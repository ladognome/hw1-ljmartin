package collectionReader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.collection.CollectionReader_ImplBase;
import org.apache.uima.examples.SourceDocumentInformation;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.FileUtils;
import org.apache.uima.util.Progress;
import org.apache.uima.util.ProgressImpl;

// Given input file, opens and reads
// File should have <ID> <text> format
// Separates them into individual attributes
// Using org.apache.uima.examples.cpe.FileSystemCollectionReader as template
public class Inputer extends CollectionReader_ImplBase {
  public static final String PARAM_INPUTFILE = "src/main/resources/data/sample.in";

  public static final String PARAM_ENCODING = "Encoding";

  // input file
  private File inFile;

  private String mEncoding;

  private int mCurrentIndex;

  private String currLine = "";
  
  private BufferedReader reader;

  public void initialize() throws ResourceInitializationException {
    File curr_file = new File(((String) getConfigParameterValue(PARAM_INPUTFILE)).trim());
    mEncoding = (String) getConfigParameterValue(PARAM_ENCODING);
    mCurrentIndex = 0;
    // if input file does not exist or is not a file, throw exception
    if (!curr_file.exists() || !curr_file.isFile()) {
      throw new ResourceInitializationException(ResourceConfigurationException.DIRECTORY_NOT_FOUND,
              new Object[] { PARAM_INPUTFILE, this.getMetaData().getName(), curr_file.getPath() });
    }
    // if it's found, add it as our input file
    inFile = curr_file;
  }

  @Override
  public void getNext(CAS aCAS) throws IOException, CollectionException {
    JCas jcas;
    try {
      jcas = aCAS.getJCas();
    } catch (CASException e) {
      throw new CollectionException(e);
    }

    // open input stream to file
    reader = new BufferedReader(new FileReader(inFile));
    if (currLine == "") {
      currLine = reader.readLine();
    }
    // put document in CAS
    jcas.setDocumentText(currLine);
    // go to next line
    currLine = reader.readLine();
  }

  @Override
  public void close() throws IOException {
      reader.close();
  }

  @Override
  public Progress[] getProgress() {
    //return new Progress[] { new ProgressImpl(mCurrentIndex, mFiles.size(), Progress.ENTITIES) };
    return null;
  }

  @Override
  public boolean hasNext() throws IOException, CollectionException {
    return (currLine != null);
  }

}
