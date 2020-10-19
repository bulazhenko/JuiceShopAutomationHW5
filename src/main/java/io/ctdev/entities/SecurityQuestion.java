package io.ctdev.entities;

import java.util.Arrays;
import java.util.Random;

public enum SecurityQuestion {
    YOUR_ELDEST_SIBLINGS_MIDDLE_NAME(1),
    MOTHERS_MAIDEN_NAME(2),
    MOTHERS_BIRTH_DATE(3),
    FATHERS_BIRTH_DATE(4),
    MATERNAL_GRANDMOTHERS_FIRST_NAME(5),
    PATERNAL_GRANDMOTHERS_FIRST_NAME(6),
    NAME_OF_YOUR_FAVORITE_PET(7),
    LAST_NAME_OF_DENTIST_WHEN_YOU_WERE_A_TEENAGER(8),
    YOUR_ZIP_CODE_WHEN_YOU_WERE_A_TEENAGER(9),
    COMPANY_YOU_FIRST_WORK_FOR_AS_AN_ADULT(10),
    YOUR_FAVORITE_BOOK(11),
    YOUR_FAVORITE_MOVIE(12),
    NUMBER_OF_ONE_OF_YOUR_CUSTOMER_OR_ID_CARDS(13);

    private final static Random random = new Random();

    private int index;

    public static SecurityQuestion randomQuestion() {
        return Arrays.asList(values()).get(random.nextInt(values().length));
    }

    SecurityQuestion(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
