package analysisEngine;

import java.util.Map;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import annotation.AnnotationObject;
import util.PosTagNamedEntityRecognizer;

public class PosTagger extends JCasAnnotator_ImplBase {

  private PosTagNamedEntityRecognizer pt;

  public void initialize(UimaContext aContext) throws ResourceInitializationException {
    super.initialize(aContext);
    pt = new PosTagNamedEntityRecognizer();
  }

  @Override
  public void process(JCas aCAS) throws AnalysisEngineProcessException {
    // The JCas object is the data object inside UIMA where all the
    // information is stored. It contains all annotations created by
    // previous annotators, and the document text to be analyzed.

    // get document text from JCas
    String docText = aCAS.getDocumentText();

    Map<Integer, Integer> pos = pt.getGeneSpans(docText);
    for (Integer key : pos.keySet()) {
      int start = key;
      int end = pos.get(key);
      String entity = docText.substring(start, end);
      AnnotationObject ann = new AnnotationObject(aCAS);
      ann.setBegin(start);
      ann.setEnd(end);
      ann.setGeneName(entity);
      ann.addToIndexes();
    }
  }
}
