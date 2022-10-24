package com.myEstes.as400.jagacy.session;
/*
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.RenderingHints.Key;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;

import com.jagacy.Session3270;
import com.jagacy.util.JagacyException;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;
import com.myEstes.as400.jagacy.fields.EntryField;
import com.myEstes.as400.jagacy.fields.LabelField;
/*
public class Session extends Session3270 {
	

	public JagacyProperties props;
	public final int DEFAULT_TIMEOUT = 15000;
	private final HashMap< Key, Object> renderingProperties = new HashMap<>();
	public Loggable logger;
	private boolean isDemo;
	private String timestamp;
	public int screenshotCounter;
	
	//private String dateFolder = getCurrentDate("yyyy-MM-dd HH:mm:ss").repaceAll(" ", "_").replaceAll(";", "-");
	
	public String getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(String timestamp) {
		
		
		this.timestamp = timestamp;
	}
	
	public Session(boolean isDemo) throws JagacyException{
		super("as400_Mainframe_Automation");
		this.isDemo = isDemo;
		
		props = getProperties();
		logger = getLoggable();
	}
	
	public Session(final String session) throws JagacyException{
		super(session);
	}
	
	public final boolean waitForTextLabel(final LabelField textLabel) throws JagacyException {
		 System.out.println(textLabel.getText());
		return waitForPosition(textLabel.getRow(), textLabel.getColumn(), textLabel.getText(), DEFAULT_TIMEOUT);
	}
	
	public final void setEntryFieldValue(final EntryField entryField, final String value) throws JagacyException {
		writePosition(entryField.getRow(), entryField.getColumn(), value);		
	}
	
	private String getCurrentDate(String dateFormat) {
		String date = null;
		Date myDate = new Date();
		date = new SimpleDateFormat(dateFormat).format(myDate);
		return date;
	}	
	
	public void isDemo() throws JagacyException {
		if(isDemo) {
			try {
				Thread.sleep(1 * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	  public final byte[] getScreenshot() throws Exception {

	        // below approach is inspired by solutions given
	        // at http://stackoverflow.com/questions/18800717/convert-text-content-to-image

	        String screenText = StringUtils.join(readScreen(), "\n");

	        renderingProperties.put(RenderingHints.KEY_ANTIALIASING,
	                RenderingHints.VALUE_ANTIALIAS_ON);
	        renderingProperties.put(RenderingHints.KEY_STROKE_CONTROL,
	                RenderingHints.VALUE_STROKE_PURE);
	        renderingProperties.put(RenderingHints.KEY_FRACTIONALMETRICS,
	                RenderingHints.VALUE_FRACTIONALMETRICS_ON);

	        Font font = new Font("Consolas", Font.PLAIN, 12);
	        FontRenderContext fontRenderContext =
	                new FontRenderContext(null, true, true);
	        BufferedImage bufferedImage =
	                new BufferedImage(600, 300, BufferedImage.TYPE_INT_ARGB);
	        Graphics2D graphics2D = bufferedImage.createGraphics();
	        graphics2D.setRenderingHints(renderingProperties);
	        graphics2D.setBackground(Color.black);
	        graphics2D.setColor(Color.GREEN);
	        graphics2D.setFont(font);
	        graphics2D.clearRect(0, 0, bufferedImage.getWidth(),
	                bufferedImage.getHeight());

	        TextLayout textLayout =
	                new TextLayout(screenText, font, fontRenderContext);

	        int count = 0;
	        for (String line : screenText.split("\n")) {
	            graphics2D.drawString(line, 15,
	                    (int) (15 + count * textLayout.getBounds()
	                            .getHeight() + 0.5));
	            count++;
	        }
	        graphics2D.dispose();

	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        ImageIO.write(bufferedImage, "PNG", out);
	        return out.toByteArray();
	    }
	
	
	
}
*/

