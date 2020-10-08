package seedu.address.model.travelplan;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.accommodation.Accommodation;
import seedu.address.model.accommodation.UniqueAccommodationList;
import seedu.address.model.commons.TravelPlanObject;

/**
 * Represents the list of Accommodations in a travel plan
 * Duplicates are not allowed (by .isSameAccommodation comparison)
 */
public class AccommodationList implements ReadOnlyAccommodationList {

    private final UniqueAccommodationList accommodations;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        accommodations = new UniqueAccommodationList();
    }

    public AccommodationList() {}

    /**
     * Creates an AccommodationList using the Accommodations in the {@code toBeCopied}
     */
    public AccommodationList(ReadOnlyAccommodationList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the accommodation list with {@code accommodations}.
     * {@code accommodations} must not contain duplicate accommodations.
     */
    public void setAccommodations(List<Accommodation> accommodations) {
        this.accommodations.setAccommodations(accommodations);
    }

    /**
     * Resets the existing data of this {@code AccommodationList} with {@code newData}.
     */
    public void resetData(ReadOnlyAccommodationList newData) {
        requireNonNull(newData);

        setAccommodations(newData.getAccommodationList());
    }

    //// accommodation-level operations

    /**
     * Returns true if a accommodation with the same identity as {@code accommodation} exists in the accommodation list.
     */
    public boolean hasAccommodation(Accommodation accommodation) {
        requireNonNull(accommodation);
        return accommodations.contains(accommodation);
    }

    /**
     * Adds a accommodation to the accommodation list.
     * The accommodation must not already exist in the accommodation list.
     */
    public void addAccommodation(Accommodation p) {
        accommodations.add(p);
    }

    /**
     * Replaces the given accommodation {@code target} in the list with {@code editedAccommodation}.
     * {@code target} must exist in the accommodation list.
     * The accommodation identity of {@code editedAccommodation} must not be the same as another existing accommodation
     * in the accommodation list.
     */
    public void setAccommodation(Accommodation target, Accommodation editedAccommodation) {
        requireNonNull(editedAccommodation);

        accommodations.setAccommodation(target, editedAccommodation);
    }

    /**
     * Removes {@code key} from this {@code AccommodationList}.
     * {@code key} must exist in the accommodation list.
     */
    public void removeAccommodation(Accommodation key) {
        accommodations.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Accommodations: ");
        getAccommodationList().forEach(builder::append);
        return builder.toString();
    }

    @Override
    public ObservableList<Accommodation> getAccommodationList() {
        return accommodations.asUnmodifiableObservableList();
    }

    public ObservableList<TravelPlanObject> getTpoList() {
        return accommodations.asUnmodifiableObservableTpoList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AccommodationList // instanceof handles nulls
                && accommodations.equals(((AccommodationList) other).accommodations));
    }

    @Override
    public int hashCode() {
        return accommodations.hashCode();
    }
}