package util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args)
		    throws ClassNotFoundException
		  {
		    SQLDataList sdl = new SQLDataList();
		    
		    List<List<String>> sqlResults1 = sdl.getDataByColumn("exlaqa", "select fhot, fhpro, fhsnm from fbfiles.frp001 where fhot = '001' and fhpro like '958554%'");
		    for (int i = 0; i <= (sqlResults1.get(0)).size() - 1; i++) {
		      System.out.println((String)((ArrayList)sqlResults1.get(0)).get(i) + ", " + (String)((ArrayList)sqlResults1.get(1)).get(i) + ", " + (String)((ArrayList)sqlResults1.get(2)).get(i));
		    }
		    List<List<String>> sqlResults2 = sdl.getDataByRow("exlaqa", "select fhot, fhpro, fhsnm from fbfiles.frp001 where fhot = '001' and fhpro like '958554%'");
		    for (int i = 0; i <= sqlResults2.size() - 1; i++) {
		      System.out.println((String)((ArrayList)sqlResults2.get(i)).get(0) + ", " + (String)((ArrayList)sqlResults2.get(i)).get(1) + ", " + (String)((ArrayList)sqlResults2.get(i)).get(2));
		    }
		    List<List<String>> sqlResults3 = sdl.getDataByColumn("cdocqa", "select shortcutcode from fmadata.dock_customers fetch first 5 rows only");
		    for (int i = 0; i <= ((ArrayList)sqlResults3.get(0)).size() - 1; i++) {
		      System.out.println((String)((ArrayList)sqlResults3.get(0)).get(i));
		    }
		  }
}
