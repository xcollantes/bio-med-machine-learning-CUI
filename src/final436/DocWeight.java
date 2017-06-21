package final436;
import java.io.*;
import java.util.*;

public class DocWeight
{
    
    //private ArrayList<Document> docs = null;
    
    //Hashmap of docs to be match the query against.  
    //String is the name of file as a string
    //and Docmuent is the Document object of each document. 
    private HashMap<String, Document> fileCabinet;
    
    public HashMap<String, Double> weights;
    
    private Document doc;
    /*
    Sets the HashMap to 
    Key: String name of document
    Value: Document object 
    */
    public DocWeight(File dir)
    {
        fileCabinet = new HashMap<>();
        
        for (File in : dir.listFiles())
        {
            try 
            {
                doc = new Document(in);
            }
            catch (Exception e)
            {
                System.err.println("Initializing docs :" + e);
            }
            
            fileCabinet.put(doc.getName(), doc);
        }
        
        System.out.println("FILE LOADING COMPLETE. ");
    }
    
   
    /*
    Returns the frequency of given term in a given document name  
    NOTE: Document name must be matching key 'xxxxx.txt' as indicated 
    in the fileCabinet 
    */
    public double getTF(String docName, String term)
    {
        double tf = 0;
        for (Map.Entry<String, Document> entry : fileCabinet.entrySet())
        {
            if (docName.equalsIgnoreCase(entry.getKey()))
            {
                Document doc = entry.getValue();
                tf = doc.getFrequency(term);
            }
        }
        
        return tf;
    }
    
    /*
    Returns the Inverse Document Frequency or the log of total documents
    over the number of documents where the query term occurs  
    */
    public double getIDF(String term)
    {
        double totalDocs = fileCabinet.size();
        //System.out.println("f(x): getIDF totalDocs: " + totalDocs);
        double docsContain = 0;
        
        //Iterate through fileCabinet searching for occurences of term 
        for (Map.Entry<String, Document> docEntry : fileCabinet.entrySet())
        {
            Document tempDoc = docEntry.getValue();
            
            //Check for occurance of term 
            if (tempDoc.contains(term))
            {
                docsContain++;
            }
        }
        return Math.log(totalDocs / docsContain);
    }
    
    /*
    Returns the HashMap containing all doc objects  
    */
    public HashMap<String, Document> getFileCabinet()
    {
        return fileCabinet;
    }
}