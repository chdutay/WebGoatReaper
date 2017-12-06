package org.owasp.webgoat.plugin;

import org.owasp.webgoat.lessons.Category;
import org.owasp.webgoat.lessons.NewLesson;

import java.util.ArrayList;
import java.util.List;

/**
 * WebGoatReaper class to start reaping
 *
 * @author WebGoat
 * @version $Id: $Id
 * @since October 12, 2016
 */
public class WebGoatReaper extends NewLesson {
    @Override
    public Category getDefaultCategory() {
        return Category.INTRODUCTION;
    }

    @Override
    public List<String> getHints() {
        return new ArrayList();
    }

    @Override
    public Integer getDefaultRanking() {
        return 1;
    }

    @Override
    public String getTitle() {
        return "webgoatreaper.title";
    }

    @Override
    public String getId() {
        return "WebGoatReaper";
    }
}
