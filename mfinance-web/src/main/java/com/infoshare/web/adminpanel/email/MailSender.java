package com.infoshare.web.adminpanel.email;

import com.infoshare.web.webconfiguration.utils.JsonSerializer;
import com.infoshare.web.webconfiguration.utils.ConfigFileReader;
import com.infoshare.web.webconfiguration.smtp.SmtpProperties;
import com.infoshare.web.adminpanel.trigger.ITriggerable;
import com.infoshare.core.appconfiguration.AppConfigurationProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import javax.mail.*;

import static com.infoshare.web.utils.ConstantsProvider.MAIL_SMTP_HOST;
import static com.infoshare.web.utils.ConstantsProvider.MAIL_SMTP_SOCKETFACTORY_PORT;
import static com.infoshare.web.utils.ConstantsProvider.MAIL_SMTP_PORT;
import static com.infoshare.web.utils.ConstantsProvider.EMAIL;
import static com.infoshare.web.utils.ConstantsProvider.PASSWORD;
import static com.infoshare.web.utils.ConstantsProvider.TARGET_EMAIL;
import static com.infoshare.web.utils.ConstantsProvider.SMTP_CONFIG_FILE_NAME;

public class MailSender implements ITriggerable {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailSender.class);
    private Properties emailProps = new Properties();
    private ReportComponents reportComponents;

    public MailSender(ReportComponents reportComponents) {
        this.reportComponents = reportComponents;
    }

    @Override
    public void executeAction() {
        try {
            this.sendEmail(reportComponents);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendEmail(ReportComponents reportComponents) throws IOException {

        this.getEmailAccountProperties();

        Session session = Session.getDefaultInstance(emailProps,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(emailProps
                                .getProperty(EMAIL), emailProps
                                .getProperty(PASSWORD));
                    }
                });
        try {

            Transport.send(new MessageGenerator(reportComponents)
                    .composeMessage(session, emailProps));
            LOGGER.info("Email sent succesfully to destination: {}", emailProps.getProperty(EMAIL));
        } catch (MessagingException e) {
            LOGGER.error("Failed to send e-mail! {} {}", e.getMessage(), e.getStackTrace());
            throw new RuntimeException(e);
        }
    }

    private Properties getEmailAccountProperties() throws IOException {

        SmtpProperties smtpProperties = this.getSMTPProps();

        emailProps.put(EMAIL, smtpProperties.getEmail());
        emailProps.put(PASSWORD, smtpProperties.getPassword());
        emailProps.put(TARGET_EMAIL, smtpProperties.getTargetEmail());
        emailProps.put(MAIL_SMTP_HOST, smtpProperties.getSmtpHost());
        emailProps.put(MAIL_SMTP_SOCKETFACTORY_PORT, smtpProperties.getSmtpPort());
        emailProps.put(MAIL_SMTP_PORT, smtpProperties.getSmtpPort());
        emailProps.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        emailProps.put("mail.smtp.auth", "true");
        return emailProps;
    }

    private SmtpProperties getSMTPProps() throws IOException {
        String smtpConfigFilePath = new AppConfigurationProvider()
                .getConfiguration()
                .getExternalResourceFilePath();

        Path path = Paths.get(smtpConfigFilePath, SMTP_CONFIG_FILE_NAME);
        String content = new ConfigFileReader(path).getFileAsString();
        return new JsonSerializer(content).getProperties();
    }

}