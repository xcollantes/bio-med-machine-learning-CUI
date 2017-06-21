package final436;

import java.util.ArrayList;

public class DocAssociation
{
    
    private String docName;
    private Double weight;
    private ArrayList<String> sentences;
    private ArrayList<String> concepts;
    
    DocAssociation(String n){
        sentences = new ArrayList<String>();
        concepts = new ArrayList<String>();
        docName = n;
    }
    
    public void setWeight(Double w) {
        weight = w;
    }
    
    public Double getWeight() {
        return weight;
    }
    
    public String getName() {
        return docName;
    }
    
    public void addSentences(String sent) {
        sentences.add(sent);
    }
    
    public void addConcepts(String con) {
        concepts.add(con);
    }
    
    public ArrayList<String> getSentences() {
        return sentences;
    }
    
    public ArrayList<String> getConcepts() {
        return concepts;
    }
}