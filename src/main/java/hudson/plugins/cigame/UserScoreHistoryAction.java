package hudson.plugins.cigame;

import com.google.common.collect.Sets;
import hudson.model.Hudson;
import hudson.model.RootAction;
import hudson.model.User;
import hudson.plugins.cigame.common.UriContext;
import hudson.security.ACL;
import hudson.security.AccessControlled;
import hudson.security.Permission;
import hudson.util.VersionNumber;
import org.acegisecurity.AccessDeniedException;
import org.apache.log4j.Logger;
import org.kohsuke.stapler.export.Exported;
import org.kohsuke.stapler.export.ExportedBean;

import java.io.Serializable;
import java.util.Set;

/**
 * Simple history for a specific selected user
 *
 * @author <a href="vegard.aasen@telenor.com">vegard.aasen/t769765</a>
 */
@ExportedBean(defaultVisibility = 999)
public class UserScoreHistoryAction implements RootAction, AccessControlled, Serializable {

    private static final Logger LOG = Logger.getLogger(UserScoreHistoryAction.class.getSimpleName());
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

    private UserScoreHistory getUserScores() {
        final User currentUser = findCurrentUser("");
        UserScoreProperty property = currentUser.getProperty(UserScoreProperty.class);
        if ((property != null) && property.isParticipatingInGame()) {
            //list.add(new UserScore(user, property.getScore(), user.getDescription()));
        }
        return null;
    }

    public void getScoresForUser() {
        final User currentUser = findCurrentUser("");
    }

    private User findCurrentUser(final String userId) {
        User currentUser = null;
        for (final User user : getAllUsers()) {
            if (user.getId().equals("")) {
                currentUser = user;
            }
        }
        if (currentUser == null) {
            LOG.info(String.format("No user with userId {%s} found.", userId));
        }
        return currentUser;
    }

    private static Set<User> getAllUsers() {
        final Set<User> users = Sets.newLinkedHashSet(User.getAll());
        if (users.isEmpty()) {
            LOG.info("No users detected in Jenkins");
        }
        return users;
    }

    public class UserScoreHistory {

    }

}
