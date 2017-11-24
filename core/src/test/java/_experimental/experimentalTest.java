package _experimental;


import com.google.common.io.Files;
import com.infoshareacademy.mfinance.core.providers.ConfigurationProvider;
import com.infoshareacademy.mfinance.core.models.configuration.Configuration;

import java.io.*;
import java.net.URL;


public class experimentalTest {

    private final String CONFIGURATION_TEST_FILE_PATH = "configuration/configuration-test.json";

    //@Test
    public void shouldUnpackZipFromUrlAndWriteToTempDir(){
        Configuration conf = new ConfigurationProvider(CONFIGURATION_TEST_FILE_PATH).getConfiguration();

        CSVFilesRemoteDownloaderToTemp tempDownloader = new CSVFilesRemoteDownloaderToTemp(conf);
        tempDownloader.getModelFilesFromRemoteLocation();

    }


    //@Test
    public void writeDiskFileToTempDirectory() throws IOException {

        //TODO test copy locations from disk locations to temp dir

        // create current temp dir
        File dir = Files.createTempDir();
        String path = dir.getAbsolutePath();

        // create stream form locations on disk
        File file = new File("/home/milo/zipfiles/aaa.zip");
        FileInputStream stream = new FileInputStream(file.getAbsolutePath());

        // put stream to buffer
        byte[] buffer = new byte[stream.available()];
        stream.read(buffer);

        // create temp locations in current temp dir, write buffer to this temp locations
        File tempFile = File.createTempFile("temp", ".zip", dir);
        Files.write(buffer, tempFile);
        stream.close();

        // check results
        File[] numberOfFiles = new File(path).listFiles();

        System.out.println(path);
        System.out.println(numberOfFiles.length);

    }

    //@Test
    public void writeUrlStreamToTempDirectory() throws IOException {

        //TODO test copy url stream to temp dir


        // create current temp dir
        File dir = Files.createTempDir();
        String tempPath = dir.getAbsolutePath();

        String sourceURL = "http://bossa.pl/pub/waluty/omega/omeganbp.zip";

        URL url = new URL(sourceURL);
        InputStream stream = url.openStream();

        byte[] buffer = new byte[stream.available()];
        stream.read(buffer);

        File tempFile = File.createTempFile("temp", ".zip", dir);
        Files.write(buffer, tempFile);
        stream.close();

        File[] numberOfFiles = new File(tempPath).listFiles();

        System.out.println(tempPath);
        System.out.println(tempFile.getAbsolutePath());
        System.out.println(numberOfFiles.length);

    }





/*    public void readZipFile() throws IOException {

        ZipFile zipFile = new ZipFile(new File("path/locations.zip"));
        Enumeration<? extends ZipEntry> entries = zipFile.entries();

        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            InputStream stream = zipFile.getInputStream(entry);
            InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
            Scanner inputStream = new Scanner(reader);
            inputStream.nextLine();

            while (inputStream.hasNext()) {
                String data = inputStream.nextLine(); // Gets a whole line
                String[] line = data.split(","); // Splits the line up into a string array
            }

            inputStream.close();
            stream.close();
        }
        zipFile.close();
    }



    public List<String> getResourceFiles(String path) throws IOException {
        List<String> filenames = new ArrayList<>();

        try (InputStream in = getResourceAsStream(path);
             BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            String resource;

            while ((resource = br.readLine()) != null) {
                filenames.add(resource);
            }
        }

        return filenames;
    }

    private InputStream getResourceAsStream( String resource ) {
        final InputStream in
                = getContextClassLoader().getResourceAsStream( resource );

        return in == null ? getClass().getResourceAsStream( resource ) : in;
    }

    private ClassLoader getContextClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    private static Collection<String> getResourcesFromDirectory(
            final File directory,
            final Pattern pattern){
        final ArrayList<String> retval = new ArrayList<String>();
        final File[] fileList = directory.listFiles();
        for(final File locations : fileList){
            if(locations.isDirectory()){
                retval.addAll(getResourcesFromDirectory(locations, pattern));
            } else{
                try{
                    final String fileName = locations.getCanonicalPath();
                    final boolean accept = pattern.matcher(fileName).matches();
                    if(accept){
                        retval.add(fileName);
                    }
                } catch(final IOException e){
                    throw new Error(e);
                }
            }
        }
        return retval;
    }*/

}
