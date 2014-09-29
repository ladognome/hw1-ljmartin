

/* First created by JCasGen Mon Sep 29 12:46:06 EDT 2014 */
package annotation;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Mon Sep 29 12:46:06 EDT 2014
 * XML source: /home/lara/workspace/hw1-ljmartin/src/main/resources/descriptors/ae-PosDescriptor.xml
 * @generated */
public class AnnotationObject extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(AnnotationObject.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected AnnotationObject() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public AnnotationObject(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public AnnotationObject(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public AnnotationObject(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: geneName

  /** getter for geneName - gets 
   * @generated
   * @return value of the feature 
   */
  public String getGeneName() {
    if (AnnotationObject_Type.featOkTst && ((AnnotationObject_Type)jcasType).casFeat_geneName == null)
      jcasType.jcas.throwFeatMissing("geneName", "annotation.AnnotationObject");
    return jcasType.ll_cas.ll_getStringValue(addr, ((AnnotationObject_Type)jcasType).casFeatCode_geneName);}
    
  /** setter for geneName - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setGeneName(String v) {
    if (AnnotationObject_Type.featOkTst && ((AnnotationObject_Type)jcasType).casFeat_geneName == null)
      jcasType.jcas.throwFeatMissing("geneName", "annotation.AnnotationObject");
    jcasType.ll_cas.ll_setStringValue(addr, ((AnnotationObject_Type)jcasType).casFeatCode_geneName, v);}    
   
    
  //*--------------*
  //* Feature: start

  /** getter for start - gets 
   * @generated
   * @return value of the feature 
   */
  public int getStart() {
    if (AnnotationObject_Type.featOkTst && ((AnnotationObject_Type)jcasType).casFeat_start == null)
      jcasType.jcas.throwFeatMissing("start", "annotation.AnnotationObject");
    return jcasType.ll_cas.ll_getIntValue(addr, ((AnnotationObject_Type)jcasType).casFeatCode_start);}
    
  /** setter for start - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setStart(int v) {
    if (AnnotationObject_Type.featOkTst && ((AnnotationObject_Type)jcasType).casFeat_start == null)
      jcasType.jcas.throwFeatMissing("start", "annotation.AnnotationObject");
    jcasType.ll_cas.ll_setIntValue(addr, ((AnnotationObject_Type)jcasType).casFeatCode_start, v);}    
   
    
  //*--------------*
  //* Feature: end

  /** getter for end - gets 
   * @generated
   * @return value of the feature 
   */
  public int getEnd() {
    if (AnnotationObject_Type.featOkTst && ((AnnotationObject_Type)jcasType).casFeat_end == null)
      jcasType.jcas.throwFeatMissing("end", "annotation.AnnotationObject");
    return jcasType.ll_cas.ll_getIntValue(addr, ((AnnotationObject_Type)jcasType).casFeatCode_end);}
    
  /** setter for end - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setEnd(int v) {
    if (AnnotationObject_Type.featOkTst && ((AnnotationObject_Type)jcasType).casFeat_end == null)
      jcasType.jcas.throwFeatMissing("end", "annotation.AnnotationObject");
    jcasType.ll_cas.ll_setIntValue(addr, ((AnnotationObject_Type)jcasType).casFeatCode_end, v);}    
   
    
  //*--------------*
  //* Feature: ID

  /** getter for ID - gets 
   * @generated
   * @return value of the feature 
   */
  public String getID() {
    if (AnnotationObject_Type.featOkTst && ((AnnotationObject_Type)jcasType).casFeat_ID == null)
      jcasType.jcas.throwFeatMissing("ID", "annotation.AnnotationObject");
    return jcasType.ll_cas.ll_getStringValue(addr, ((AnnotationObject_Type)jcasType).casFeatCode_ID);}
    
  /** setter for ID - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setID(String v) {
    if (AnnotationObject_Type.featOkTst && ((AnnotationObject_Type)jcasType).casFeat_ID == null)
      jcasType.jcas.throwFeatMissing("ID", "annotation.AnnotationObject");
    jcasType.ll_cas.ll_setStringValue(addr, ((AnnotationObject_Type)jcasType).casFeatCode_ID, v);}    
  }

    