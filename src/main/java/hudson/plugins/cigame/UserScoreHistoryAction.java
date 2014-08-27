package hudson.plugins.cigame;

import hudson.model.Hudson;
import hudson.model.RootAction;
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

    private static final long serialVersionUID = 42L;

    @Override
    public ACL getACL() {
        return null;
    }

    @Override
    public void checkPermission(Permission permission) throws AccessDeniedException {

    }

    @Override
    public boolean hasPermission(Permission permission) {
        return false;
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
        return null;
    }

    @Exported
    public boolean isUserAvatarSupported() {
        return new VersionNumber(Hudson.VERSION).isNewerThan(new VersionNumber("1.433"));
    }
}
