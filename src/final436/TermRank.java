package final436;
import java.util.*;
import java.io.*;
/*
Calculate the query: query.txt,
use acc. TF-IDF to find docs with the queries.  
*/
public class TermRank 
{
    public Map<String, Double> rank(String queryFileIn, String CUIFilesIn)
    {
        
        //TXT file containing query terms 
        final File queryFile = new File(queryFileIn);
        final File CUIFiles = new File(CUIFilesIn);
        
        ArrayList<String> query = null;
        String queryTermStr = null;
        
        //Parse query file
        try
        {
            BufferedReader queryReader = new BufferedReader(new FileReader(queryFile));
            
            //Read in query txt file into queryTermStr then parse into array 
            String temp = null;
            while ((temp = queryReader.readLine()) != null)
            {
                queryTermStr += temp;
            }
            
            //Turn the queryTermStr into an array for use later 
            String strArr[] = queryTermStr.split("\\s");
            
            //Remove any null values 
            /*for (int i = 0; i < query.size(); i++)
            {
                if (query.get(i) == null)
                {
                    query.remove(i);
                    System.out.println("NULL REMOVED");
                }
            }
            */
            
            //Put all of strArr[] into ArrayList  
            query = new ArrayList<>(Arrays.asList(strArr));
            
            query.remove(0);
            
            //query.removeAll(Collection.singleton(null));
            
            queryReader.close();
            System.out.println("Query ArrayList contains: " + query.size());
        }
        catch (IOException e)
        {
            System.out.println("queryReader error: " + e);
        }
        
        DocWeight docSet = null;
        try
        {
           docSet = new DocWeight(CUIFiles); 
        }
        catch (Exception e)
        {
            System.err.println("Initializing of DocWeight: " + e);
        }
        
        //Get TF*IDF for each doc using query 
        
        
        
        //HashMap of String docName in format 'xxxx.txt' with 
        //value of the acc. TFIDF  
        HashMap<String, Double> accWeight = new HashMap<>();
        
        
        //Iterate for each document in fileCabinet  
        for (Map.Entry<String, Document> entry : docSet.getFileCabinet().entrySet())
        {
            //Reset acc every document 
            double accTFIDF = 0;
            
            //Run through query list for each document 
            for (String str : query)
            {
                
                
                double tf = 0;
                double idf = 0;
                tf = docSet.getTF(entry.getKey(), str);
                idf = docSet.getIDF(str);
                accTFIDF = accTFIDF + (tf * idf);
                System.out.println("Evaluating: " + str + " for " + entry.getKey() + "TF: " + tf + " IDF: " + idf);  
            }
            
            accWeight.put(entry.getKey(), accTFIDF);
            
            System.out.println("Acc. TF-IDF assigned to " + entry.getKey() + " is " + accTFIDF);
        }
        
        System.out.println("******LOADING ACC. TF-IDF COMPLETE!******");
        
        //Load HashMap accWeight into doc for faster retreival 
        /*try
        {
            PrintWriter pw = new PrintWriter(new File("C:\\Users\\xcollantes\\Downloads\\assignment11\\accWeight\\"));
            for (StringaccWeight)
        }
        catch (IOException e)
        {
            System.err.println(e);
        }
*/
        
        //Order the accWeight HashMap in descending order by value  
        Set<Map.Entry<String, Double>> map = accWeight.entrySet();
        
        //Turn map into linked list for this is faster 
        List<Map.Entry<String, Double>> list = new LinkedList<>(map);
        
        //The sorting of the list  
        Collections.sort(list, (Map.Entry<String, Double> element1, Map.Entry<String, Double> element2) -> element1.getValue().compareTo(element2.getValue()) //@Override
        );
        
        //Storing the list into Linked HashMap to preserve the order of insertion  
        Map<String, Double> sortedWeights = new LinkedHashMap<>();
        
        for (Map.Entry<String, Double> entry : list) 
        {
            sortedWeights.put(entry.getKey(), entry.getValue());
        }
        
        //Printing values after sorting of map 
        System.out.println("Value " + " - " + "Key");
        
        for (Map.Entry<String, Double> entry : sortedWeights.entrySet()) 
        {
            System.out.println(entry.getValue() + " - " + entry.getKey());
        }
        
        return sortedWeights;
    }
    
}
