package analysisEngine;

import java.util.Map;

import objects.AnnotationObject;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import util.PosTagNamedEntityRecognizer;

/*
 * Wrapper to call PosTagNamedEntityRecognizer
 * Gets word from text, given the positions
 * Removes whitespaces in positions
 * Updates CAS with annotation
 * Used org.apache.uima.examples.cas.RegExAnnotator as template
 */

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

    // call PosTagNamedEntityRecognizer's span finder
    Map<Integer, Integer> pos = pt.getGeneSpans(docText);
    iterateMap(pos, docText, aCAS);

  }

  /**
   * Given a string, find the number of whitespace characters
   *
   * @param input
   *          a string
   * @return the count of whitespaces
   */
  private int countWhitespace(String input) {
    // given a string, find the number of whitespace characters
    int counter = 0;
    for (int i = 0; i < input.length(); i++) {
      if (Character.isWhitespace(input.charAt(i))) {
        counter++;
      }
    }
    return counter;
  }

  /**
   * Adding annotations to CAS from a map of positions
   *
   * @param m
   *          a map with starting positions(int) for keys and end positions(int) as values
   * @param doc
   *          a string
   * @param aCAS
   *          the JCas you want to add the annotations to
   * @return void
   */
  private void iterateMap(Map<Integer, Integer> m, String doc, JCas aCAS) {
    // for each mapping from PosTagNamedEntityRecognizer
    for (Integer key : m.keySet()) {
      int start = key;
      int end = m.get(key);
      String entity = doc.substring(start, end);

      // fixing indices to remove whitespace
      int preEntitySpaceCount = countWhitespace(doc.substring(0, start));
      int entitySpaceCount = countWhitespace(entity);

      // add annotation to the CAS
      AnnotationObject ann = new AnnotationObject(aCAS);
      ann.setBegin(start - preEntitySpaceCount);
      ann.setEnd(end - preEntitySpaceCount - entitySpaceCount - 1);
      ann.setGeneName(entity);
      ann.addToIndexes();
    }
  }
}
