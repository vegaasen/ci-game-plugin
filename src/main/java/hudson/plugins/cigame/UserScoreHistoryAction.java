package hudson.plugins.cigame;

import hudson.model.Hudson;
import hudson.model.RootAction;
import hudson.plugins.cigame.common.UriContext;
import hudson.security.ACL;
import hudson.security.AccessControlled;
import hudson.security.Permission;
import hudson.util.VersionNumber;
import org.acegisecurity.AccessDeniedException;
import org.kohsuke.stapler.export.Exported;
import org.kohsuke.stapler.export.ExportedBean;

import java.io.Serializable;

/**
 * Simple history for a specific selected user
 *
 * @author <a href="vegard.aasen@telenor.com">vegard.aasen/t769765</a>
 */
@ExportedBean(defaultVisibility = 999)
public class UserScoreHistoryAction implements RootAction, AccessControlled, Serializable {

    private static final String URL = String.format("%s/%s", UriContext.CI_GAME_HOME, UriContext.CI_GAME_USER_SCORES);
    private static final long serialVersionUID = 42L;

    @Exported
    public boolean isUserAvatarSupported() {
        return new VersionNumber(Hudson.VERSION).isNewerThan(new VersionNumber("1.433"));
    }

    @Override
    public String getIconFileName() {
        return GameDescriptor.ACTION_LOGO_MEDIUM;
    }

    @Override
    public String getDisplayName() {
        return null;
    }

    @Override
    public String getUrlName() {
        return URL;
    }

    @Override
    public ACL getACL() {
        return Hudson.getInstance().getACL();
    }

    @Override
    public void checkPermission(final Permission permission) throws AccessDeniedException {
        getACL().checkPermission(permission);
    }

    @Override
    public boolean hasPermission(final Permission permission) {
        return getACL().hasPermission(permission);
    }
}
