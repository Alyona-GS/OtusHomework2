package components;

import com.google.inject.Inject;
import pageObject.AbsPageObject;
import support.GuiceScoped;

public abstract class AbsComponent<T> extends AbsPageObject<T> {

    @Inject
    public AbsComponent(GuiceScoped guiceScoped) {
        super(guiceScoped);
    }
}
