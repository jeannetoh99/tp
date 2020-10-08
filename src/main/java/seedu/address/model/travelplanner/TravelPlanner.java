package seedu.address.model.travelplanner;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.model.activity.Activity;
import seedu.address.model.travelplan.TravelPlan;
import seedu.address.model.travelplan.UniqueTravelPlanList;
import seedu.address.model.wishlist.Wishlist;

/**
 * Wraps all data at the travel planner level
 * Duplicates are not allowed (by .isSameTravelPlan comparison)
 */
public class TravelPlanner implements ReadOnlyTravelPlanner {

    private final UniqueTravelPlanList travelPlans;
    private final Wishlist wishlist;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        travelPlans = new UniqueTravelPlanList();
        wishlist = new Wishlist();
    }

    public TravelPlanner() {}

    /**
     * Creates an TravelPlanner using the TravelPlans in the {@code toBeCopied}
     */
    public TravelPlanner(ReadOnlyTravelPlanner toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the travel plan list with {@code travelPlans}.
     * {@code travelPlans} must not contain duplicate travel plans.
     */
    public void setTravelPlans(List<TravelPlan> travelPlans) {
        this.travelPlans.setTravelPlans(travelPlans);
    }

    /**
     * Replaces the contents of the wishlist with {@code activities}.
     * {@code activities} must not contain duplicate activities.
     */
    public void setWishlist(List<Activity> activities) {
        this.wishlist.setActivities(activities);
    }

    /**
     * Resets the existing data of this {@code TravelPlanner} with {@code newData}.
     */
    public void resetData(ReadOnlyTravelPlanner newData) {
        requireNonNull(newData);

        setTravelPlans(newData.getTravelPlanList());
        setWishlist(newData.getWishlist());
    }

    //// person-level operations

    /**
     * Returns true if a travel plan with the same identity as {@code travelPlan} exists in the travel planner.
     */
    public boolean hasTravelPlan(TravelPlan travelPlan) {
        requireNonNull(travelPlan);
        return travelPlans.contains(travelPlan);
    }

    /**
     * Returns true if an activity with the same identity as {@code activity} exists in the wishlist.
     */
    public boolean hasActivity(Activity activity) {
        return wishlist.hasActivity(activity);
    }

    /**
     * Adds a travel plan to the travel planner.
     * The travel plan must not already exist in the travel planner.
     */
    public void addTravelPlan(TravelPlan tP) {
        travelPlans.add(tP);
    }

    /**
     * Adds an activity to the wishlist.
     */
    public void addActivity(Activity activity) {
        wishlist.addActivity(activity);
    }

    /**
     * Replaces the given travel plan {@code target} in the list with {@code editedTravelPlan}.
     * {@code target} must exist in the travel planner.
     * The travel plan identity of {@code editedTravelPlan} must not be the same as another existing travel plan in the
     * travel planner.
     */
    public void setTravelPlan(TravelPlan target, TravelPlan editedTravelPlan) {
        requireNonNull(editedTravelPlan);
        travelPlans.setTravelPlan(target, editedTravelPlan);
    }

    /**
     * Replaces the given activity {@code target} in the list with {@code editedActivity}.
     * {@code target} must exist in the wishlist.
     * The activity identity of {@code editedActivity} must not be the same as another existing activity in the
     * wishlist.
     */
    public void setActivity(Activity target, Activity editedActivity) {
        wishlist.setActivity(target, editedActivity);
    }

    /**
     * Removes {@code key} from this {@code TravelPlanner}.
     * {@code key} must exist in the travel planner.
     */
    public void removeTravelPlan(TravelPlan key) {
        travelPlans.remove(key);
    }

    /**
     * Removes {@code key} from this {@code Wishlist}.
     * {@code key} must exist in the wishlist.
     */
    public void removeActivity(Activity key) {
        wishlist.removeActivity(key);
    }

    /**
     * Returns the wishlist as a Wishlist.
     * Used to initialize the directory.
     */
    public Wishlist getWishlistAsDirectory() {
        return wishlist;
    }

    //// util methods

    @Override
    public String toString() {
        return travelPlans.asUnmodifiableObservableList().size() + " travel plans\n"
                + wishlist.getActivityList().size() + " activities";
        // TODO: refine later
    }

    @Override
    public ObservableList<TravelPlan> getTravelPlanList() {
        return travelPlans.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Activity> getWishlist() {
        return wishlist.getActivityList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TravelPlanner // instanceof handles nulls
                && travelPlans.equals(((TravelPlanner) other).travelPlans)
                && wishlist.equals(((TravelPlanner) other).wishlist));
    }

    @Override
    public int hashCode() {
        // TODO: need consider wishlist's hashcode also
        return Objects.hash(travelPlans, wishlist);
    }
}