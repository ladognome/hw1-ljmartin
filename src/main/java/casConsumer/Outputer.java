package casConsumer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.collection.base_cpm.CasObjectProcessor;
import org.apache.uima.examples.SourceDocumentInformation;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;

// Outputs annotations in <ID>|<start> <end>|<entity> form
// Prints to file
// Used org.apache.uima.examples.cpe.AnnotationPrinter as template

public class Outputer extends CasConsumer_ImplBase implements CasObjectProcessor {
  File outFile;

  FileWriter fileWriter;
  
  //private String outputFile = "src/main/java/data/hypothesis.out";

  public Outputer() {
  }

  public void initialize() throws ResourceInitializationException {

    // extract configuration parameter settings
    String oPath = (String) getUimaContext().getConfigParameterValue("outputFile");

    // Output file should be specified in the descriptor
    if (oPath == null) {
      throw new ResourceInitializationException(
              ResourceInitializationException.CONFIG_SETTING_ABSENT, new Object[] { "outputFile" });
    }
    // If specified output directory does not exist, try to create it
    outFile = new File(oPath.trim());
    if (outFile.getParentFile() != null && !outFile.getParentFile().exists()) {
      if (!outFile.getParentFile().mkdirs())
        throw new ResourceInitializationException(
                ResourceInitializationException.RESOURCE_DATA_NOT_VALID, new Object[] { oPath,
                    "outputFile" });
    }
    try {
      fileWriter = new FileWriter(outFile);
    } catch (IOException e) {
      throw new ResourceInitializationException(e);
    }
  }
  
  
  
  @Override
  public void processCas(CAS aCAS) throws ResourceProcessException {
    JCas jcas;
    try {
      jcas = aCAS.getJCas();
    } catch (CASException e) {
      throw new ResourceProcessException(e);
    }

    boolean titleP = false;
    String docUri = null;
    Iterator it = jcas.getAnnotationIndex(SourceDocumentInformation.type).iterator();
    if (it.hasNext()) {
      SourceDocumentInformation srcDocInfo = (SourceDocumentInformation) it.next();
      docUri = srcDocInfo.getUri();
    }

    // iterate and print annotations
    Iterator annotationIter = jcas.getAnnotationIndex().iterator();
    while (annotationIter.hasNext()) {
      Annotation annot = (Annotation) annotationIter.next();
      if (titleP == false) {
        try {
          if (docUri != null)
            fileWriter.write(docUri + "|" + annot.getBegin() + " " + annot.getEnd() + "|"+ annot.getCoveredText()+"\n");
        } catch (IOException e) {
          throw new ResourceProcessException(e);
        }
        titleP = true;
      }
      // get the text that is enclosed within the annotation in the CAS
      String aText = annot.getCoveredText();
      aText = aText.replace('\n', ' ');
      aText = aText.replace('\r', ' ');
      // System.out.println( annot.getType().getName() + " "+aText);
      try {
        fileWriter.write(annot.getType().getName() + " " + aText + "\n");
        fileWriter.flush();
      } catch (IOException e) {
        throw new ResourceProcessException(e);
      }
    }

  }
  
  
  public void destroy() {
    if (fileWriter != null) {
      try {
        fileWriter.close();
      } catch (IOException e) {
        // ignore IOException on destroy
      }
    }
  }

}
