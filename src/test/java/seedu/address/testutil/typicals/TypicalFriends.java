package seedu.address.testutil.typicals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.friend.Friend;
import seedu.address.model.travelplan.FriendList;
import seedu.address.testutil.builders.FriendBuilder;

/**
 * A utility class containing a list of {@code Friend} objects to be used in tests.
 */
public class TypicalFriends {

    public static final Friend ALICE = new FriendBuilder().withName("Alice Pauline")
            .withPassport("A1234567")
            .withPhone("94351253").build();
    public static final Friend BENSON = new FriendBuilder().withName("Benson Meier")
            .withPassport("B1234567")
            .withPhone("98765432").build();
    public static final Friend CARL = new FriendBuilder().withName("Carl Kurz")
            .withPassport("C1234567")
            .withPhone("95352563").build();
    public static final Friend DANIEL = new FriendBuilder().withName("Daniel Meier")
            .withPassport("D1234567")
            .withPhone("87652533").build();

    public static final Friend ELLE = new FriendBuilder().withName("Elle Meyer")
            .withPassport("E1234567")
            .withPhone("94822241").build();
    public static final Friend FIONA = new FriendBuilder().withName("Fiona Kunz")
            .withPassport("F7654321")
            .withPhone("94822427").build();
    public static final Friend GEORGE = new FriendBuilder().withName("George Best")
            .withPassport("G7654321")
            .withPhone("94824142").build();
    public static final Friend HOON = new FriendBuilder().withName("Hoon Meier")
            .withPassport("H7654321")
            .withPhone("84824224").build();
    public static final Friend IDA = new FriendBuilder().withName("Ida Mueller")
            .withPassport("I7654321")
            .withPhone("84821631").build();

    private TypicalFriends() {} // prevents instantiation

    /**
     * Returns a {@code FriendList} with a set of typical friends (either set 1 or 2).
     */
    public static FriendList getTypicalFriendList(int set) {
        if (set != 1 && set != 2) {
            throw new IllegalArgumentException("getTypicalFriendList only takes in set 1 or 2 as argument.");
        }
        List<Friend> friends = set == 1 ? getTypicalFriends1() : getTypicalFriends2();
        FriendList fl = new FriendList();
        for (Friend friend : friends) {
            fl.addFriend(friend);
        }
        return fl;
    }

    public static List<Friend> getTypicalFriends1() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL));
    }

    public static List<Friend> getTypicalFriends2() {
        return new ArrayList<>(Arrays.asList(ELLE, FIONA, GEORGE, HOON, IDA));
    }

}