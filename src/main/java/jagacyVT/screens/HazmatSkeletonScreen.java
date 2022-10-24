package jagacyVT.screens;

import com.jagacy.Key;
import com.jagacy.SessionVt;
import com.jagacy.util.JagacyProperties;
import com.jagacy.util.Loggable;
import jagacy.util.JagacyUtil;

public class HazmatSkeletonScreen {

    private SessionVt session;
    public Loggable logger;
    public JagacyProperties props;

    public HazmatSkeletonScreen(SessionVt s) {
        this.session =s;
        props = s.getProperties();
        logger = s.getLoggable();
    }

    public final void enterZone(String zone) throws Exception {
        logger.info("Enter Zone");
        session.waitForChange(3000);
        session.writeCursor(19, 70);
        session.writePosition(19, 70, zone);
        session.waitForChange(1000);
        session.writeKey(Key.ENTER);
        session.waitForChange(3000);
    }

}