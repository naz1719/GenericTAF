package definition.ui;

import adaptation.ui.driver.WebDriverManager;
import execution.logger.TestLogger;
import features.wait.UIWaitManager;

public abstract class BaseBO {
    protected UIWaitManager waitManager = UIWaitManager.getInstance();
    protected TestLogger LOG = TestLogger.getLogger();
    private BasePO basePO;

    public BaseBO() {
        basePO = new BasePO();
    }

    public void openPortal(String url) {
        step("Go to " + url);
        WebDriverManager.getUrl(url);
    }

    public void error(String message) {
        LOG.error(message);
    }

    public void step(String message) {
        LOG.info(message);
    }
}
