package com.bny.esg.dto;

import java.security.SecureRandom;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import com.bny.esg.model.User;

public final class DefaultUserDao implements UserDao {

    final static private String[] UserRoles = {"Basic", "Space", "Aquatic", "Marine", "Jump", "Sand"};
    final static private String[] UserNames = {"Coruscant", "Tatooine", "Felucia", "Hoth", "Naboo", "Serenno"};
    final static private String[] OrgIds= {"Human", "Kel Dor", "Nikto", "Twi'lek", "Unidentified"};
    final static private Random RANDOM = new SecureRandom();

    final private Map<String, User> userMap = Collections.synchronizedSortedMap(new TreeMap<String, User>());

    public DefaultUserDao() {
        for (int i = 0; i < 50; i++) {
            addUser(randomUser());
        }
    }

    @Override
    public Collection<User> listUsers() {
        return Collections.unmodifiableCollection(userMap.values());
    }

    @Override
    public User getUser(String id) {
        return userMap.get(id);
    }

    @Override
    public User addUser(User user) {
        if (user.getId() == null || user.getId().trim().isEmpty()) {
        	user.setId(generateRandomId());
        }
        userMap.put(user.getId(), user);
        return user;
    }

    @Override
    public User updateUser(String id, User user) {
        // we are just backing with a map, so just call add.
        return addUser(user);
    }

    @Override
    public boolean deleteUser(String id) {
        return userMap.remove(id) != null;
    }


    ///////////////////////////////////
    //  Dummy data generating below  //
    ///////////////////////////////////

    private static User randomUser(String id) {
        String userName = UserNames[RANDOM.nextInt(UserNames.length)];
        String orgId = OrgIds[RANDOM.nextInt(OrgIds.length)];
        String userRole = UserRoles[RANDOM.nextInt(UserRoles.length)];

        return new User(id, userName, orgId, userRole);
    }

    private static String generateRandomId() {
        // HIGH chance of collisions, but, this is all for fun...
        return "FN-"  + String.format("%04d", RANDOM.nextInt(9999));
    }

    private static User randomUser() {
        return randomUser(generateRandomId());
    }
}
