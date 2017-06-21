package final436;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Precision
{
    Map<String, Double> docs;
    ArrayList<String> goldStandard;
    ArrayList<String> relevantDocs;
    
    public Precision(Map<String, Double> sortedDocs, String goldStandardFile) {
        docs = sortedDocs;
        goldStandard = new ArrayList<>();
        String goldStandardAddress = goldStandardFile;
        String line = null;
        
        try 
        {
            BufferedReader br = new BufferedReader(new FileReader(goldStandardFile));
            while ((line = br.readLine()) != null)
            {
                StringTokenizer st = new StringTokenizer(line, ", ");
                while(st.hasMoreTokens()) {
                    String token = st.nextToken();
                    goldStandard.add(token);
                }
                
            }
        }
        catch (Exception e)
        {
            System.err.println(e.fillInStackTrace());
        }
        
        
        
        
        
    }
    
    public double pAt10() {
        double relevantCount = 0;
        List<String> keys = new ArrayList<String>(docs.keySet());
        
        for(int i = docs.size() - 1; i > docs.size() - 11; i --) {
            for(int j = 0; j < goldStandard.size(); j++) {
                if(goldStandard.get(j).equalsIgnoreCase(keys.get(i))) {
                    relevantCount++;
                }
            }
        }
        System.out.println("P@10 = " + relevantCount/10);
        return relevantCount/10;
    }
    
    public double aveP() {
        double relevantCount = 0;

        List<String> keys = new ArrayList<String>(docs.keySet());
        List<Double> relPrec = new ArrayList<Double>();
        List<Double> avePatQ = new ArrayList<Double>();
        
        for(int i = 0; i < keys.size(); i ++) {
            for(int j = 0; j < goldStandard.size(); j++) {
                if(goldStandard.get(j).equalsIgnoreCase(keys.get(i))) {
                    relevantCount++;
                    relPrec.add(relevantCount/i);
                    avePatQ.add(getAve(relPrec));
                }
            }
        }
       /* for(int i = 0; i < relPrec.size(); i ++) {
            sum = sum + relPrec.get(i);
        }*/
       Double aveP = getAve(relPrec);
        System.out.println("AveP = " + aveP);
        System.out.println(mAveP(avePatQ, docs.size()));
        return aveP;     
    }
    
    public double getAve(List<Double> list){
        double sum = 0;
        for(int i = 0; i < list.size(); i ++) {
            sum = sum + list.get(i);
        }
        return sum/list.size();
    }
    public double mAveP(List<Double> avePatQ, int n) {
        double sum = 0;
        for(int i = 0; i < avePatQ.size(); i++) {
            sum = sum + avePatQ.get(i);
        }
        double meanavep = sum / n; 
        return meanavep;
    }
    
    
    
}