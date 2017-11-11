package com.infoshare.web.adminpanel.email;

import com.infoshare.web.analyzer.analysis.model.PersistedInvestmentRevenueCriteria;
import com.infoshare.web.utils.ConstantsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MessageGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageGenerator.class);
    private ReportComponents reportComponents;
    private final String MESSAGE_INFO = "Scheduled Report.";


    public MessageGenerator(ReportComponents reportComponents) {
        this.reportComponents = reportComponents;
    }

    public Message composeMessage(Session session, Properties properties) throws IOException {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(properties.getProperty(ConstantsProvider.EMAIL)));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(properties.getProperty(ConstantsProvider.TARGET_EMAIL)));

            message.setSubject(MESSAGE_INFO);
            message.setText(MESSAGE_INFO);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(this.generateEmailAttachment());

            message.setContent(multipart);

            return message;

        } catch (MessagingException e) {
            LOGGER.error("Failed to generate message email components: {} {}", e.getMessage(), e.getStackTrace());
            throw new RuntimeException(e);
        }
    }

    private BodyPart generateEmailAttachment() throws MessagingException, IOException {

        List<String> lines = this.generateCSVLines(reportComponents);

        BodyPart messageBodyPart = new MimeBodyPart();

        FileWriter fw = new FileWriter(ConstantsProvider.TMP_FILE_NAME);
        for (String item : lines) {
            fw.write(item);
        }
        fw.close();

        File tempFilePath = new File(ConstantsProvider.TMP_FILE_NAME);
        String absolutePath = tempFilePath.getAbsolutePath();
        LOGGER.info("absolute locations path {}", absolutePath);

        DataSource source = new FileDataSource(absolutePath);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName("report.csv");
        return messageBodyPart;
    }

    private List<String> generateCSVLines(ReportComponents reportComponents) {

        List<String> lines = new ArrayList<>();

        List<PersistedInvestmentRevenueCriteria> criteria = reportComponents
                .getFavouriteService()
                .getAllRevenueCriteria();

        lines.add("InvestmentName,InvestedCapital,BuyDate,SellDate"
                .concat(System.getProperty(ConstantsProvider.LINE_SEPARATOR)));

        // fixme

/*        for (InvestmentRevenueCriteria item : criteria) {

            String separator = ",";
            StringBuilder sb = new StringBuilder();

            sb.append(item.getInvestmentName()).append(separator);
            sb.append(item.getInvestedCapital()).append(separator);
            sb.append(item.getBuyDate().toString()).append(separator);
            sb.append(item.getSellDate().toString());
            sb.append(System.getProperty(ConstantsProvider.LINE_SEPARATOR));
            lines.add(sb.toString());
            LOGGER.info("Adding Line to CSV: {}", sb.toString());
        }
        */
        return lines;
    }

}



