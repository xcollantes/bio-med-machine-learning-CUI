package final436;

import java.io.*;
import java.util.*;

public class Document 
{
    private BufferedReader br;
    private File docPath;
    private ArrayList<String> docTerms;
    private StringTokenizer st;
    DocAssociation doc;
    
    public Document(File inDocPath)
    {
        docPath = inDocPath;
        docTerms = new ArrayList<>();
        doc = new DocAssociation(docPath.toString());
        
        //Reads in the document 
        try 
        {
            String str = null;
            br = new BufferedReader(new FileReader(docPath));
            //Read in the document 
            while ((str = br.readLine()) != null)
            {
                doc.addSentences(str);
                //Place all terms in ArrayList 
                String[] tempArr = str.split("\\W+");                
                for (String entry : tempArr)
                {
                    if (!entry.isEmpty())
                    {
                        if(!doc.getConcepts().contains(entry)) {
                            doc.addConcepts(entry);
                        }
                        docTerms.add(entry);
                    }
                }
            }
            
            System.out.println("Finished loading..." + docPath);
            br.close();
        }
        catch (IOException e)
        {
            System.err.println(e);
        }
    }
    
    public DocAssociation getDocAssociation() {
        return doc;
    }
    
    /*
    Returns the Term Fequency in the document as int 
    Term Frequency is the number of times that term is in the document 
    */
    public double getFrequency(String term)
    {
        double count = 0;
        double total = 0;
        for (String entry : docTerms)
        {
            total++;
            if (term.equals(entry))
            {
                count++;
            }
        }
        
        return count / total;
    }
    
    
    /*
    Checks if the given term exists in the document.  
    Returns a boolean true if exists, false if does not exists.  
    */
    public boolean contains(String term)
    {
        for (String entry : docTerms)
        {
            if (term.equals(entry))
            {
                return true;
            }
        }
        
        return false;
    }
    
    /*
    Returns file name with the file extension attached.  
    */
    public String getName()
    {
        return docPath.getName();
    }
    
    /*
    Prints out the contents of the documents 
    */
    public void toPrint()
    {
        for (String entry : docTerms)
        {
           System.out.print(entry + " ");
        }
    }
}
