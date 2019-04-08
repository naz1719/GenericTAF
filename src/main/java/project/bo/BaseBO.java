package project.bo;

import project.po.BasePO;
import execution.logger.TestLogger;
import features.wait.WaitManager;
import adaptation.ui.driver.WebDriverManager;

public abstract class BaseBO {
    protected WaitManager waitManager = new WaitManager();
    protected TestLogger LOG = TestLogger.getLogger();
    private BasePO basePO;

    public BaseBO() {
        basePO = new BasePO();
    }

    public void openPortal(String url) {
        step("Go to "+url);
        WebDriverManager.getUrl(url);
    }

    public void error(String message) {
        LOG.error(message);
    }

    public void step(String message) {
        LOG.info(message);
    }
}
