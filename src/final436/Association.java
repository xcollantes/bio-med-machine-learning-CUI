package final436;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Association
{
    public double associationMining(DocAssociation query, ArrayList<DocAssociation> z) {
        Double fQ = 0.0;
        Double fC = 0.0;
        Double fQC = 0.0;
        Double cnt = 0.0;
        ArrayList<String> c = new ArrayList<String>();
        c = query.getConcepts();
        
       for (int i = 0; i < z.size(); i++) {
           ArrayList<String> sent = z.get(i).getSentences();
           for(int j = 0; j < c.size(); j++) {
               for(int k = 0; k < sent.size(); k++) {
                   StringTokenizer st = new StringTokenizer(sent.get(k));
                   while(st.hasMoreTokens()) {
                       if(st.equals(c.get(j))) {
                           cnt++;
                           break;
                       }
                   }
               }
           }
           
       }
       
    }
}