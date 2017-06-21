
package final436;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author xcollantes
 */
public class Final436 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        String queryFiles = "E:\\CPEN\\final436\\Topic & Data Set I\\Topic & Data Set I\\topic I.txt";
        String CUIFiles = "E:\\CPEN\\final436\\Topic & Data Set I\\Topic & Data Set I\\CUI Files I";
        String goldStandardFile = "E:\\CPEN\\final436\\Topic & Data Set I\\Topic & Data Set I\\goldstandard I .txt";
        
       // TermRank iniRank = new TermRank();
        Association association = new Association();
        ArrayList<DocAssociation> z = new ArrayList<DocAssociation>();
        DocAssociation q = new DocAssociation(queryFiles);
        Final436 final436 = new Final436();
        
        Document qu = new Document(new File(queryFiles));
        q = qu.getDocAssociation();
        z = final436.readGoldStandard(goldStandardFile);
        
        
        
        
       // Map<String, Double> iniSortedDocs = new HashMap<String, Double>();
        //iniSortedDocs = iniRank.rank(queryFiles, CUIFiles);
        
        
        //Precision pre = new Precision(iniSortedDocs, goldStandardFile);
        
        //pre.pAt10();
        //pre.aveP();
        
        //association.associationMining(CUIFiles, goldStandardFile);
        
        
    }
    
    public ArrayList<DocAssociation> readGoldStandard(String goldStandardFile) {
        ArrayList<DocAssociation> list = new ArrayList<DocAssociation>();
        //Reads in the document 
        try 
        {
            String str = null;
            BufferedReader br = new BufferedReader(new FileReader(goldStandardFile));
            //Read in the document 
            while ((str = br.readLine()) != null)
            {
                //Place all terms in ArrayList 
                String[] tempArr = str.split("\\W+");                
                for (String entry : tempArr)
                {
                    if (!entry.isEmpty())
                    {
                        String filepath = "E:\\CPEN\\final436\\Topic & Data Set I\\Topic & Data Set I\\CUI Files I\\" + entry;
                        DocAssociation doc = new DocAssociation(filepath);
                        list.add(doc);
                    }
                }
            }

            br.close();
        }
        catch (IOException e)
        {
            System.err.println(e);
        }
        return list;
    }
    
}
