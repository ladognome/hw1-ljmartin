package collectionReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.collection.CollectionReader_ImplBase;
import org.apache.uima.examples.SourceDocumentInformation;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Progress;

// Given input file, opens and reads
// Each line is its own CAS
// Using org.apache.uima.examples.cpe.FileSystemCollectionReader as template
public class Inputer extends CollectionReader_ImplBase {
  public static final String PARAM_INPUTFILE = "src/main/resources/data/sample.in";

  public static final String PARAM_ENCODING = "Encoding";

  // input file
  private File inFile;

  private String currLine = "";

  private BufferedReader reader;

  public void initialize() throws ResourceInitializationException {
    File curr_file = new File(((String) getConfigParameterValue(PARAM_INPUTFILE)).trim());
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
    int firstSpace = currLine.indexOf(' ');
    String id = currLine.substring(0,firstSpace);
    String text = currLine.substring(firstSpace+1);
    jcas.setDocumentText(text);
    SourceDocumentInformation srcDocInfo = new SourceDocumentInformation(jcas);
    srcDocInfo.setUri(id);
    srcDocInfo.setOffsetInSource(0);
    srcDocInfo.setDocumentSize(text.length());
    srcDocInfo.addToIndexes();
    
    // go to next line
    currLine = reader.readLine();
  }

  @Override
  public void close() throws IOException {
    reader.close();
  }

  @Override
  public Progress[] getProgress() {
    // return new Progress[] { new ProgressImpl(mCurrentIndex, mFiles.size(), Progress.ENTITIES) };
    return null;
  }

  @Override
  public boolean hasNext() throws IOException, CollectionException {
    return (currLine != null);
  }

}
