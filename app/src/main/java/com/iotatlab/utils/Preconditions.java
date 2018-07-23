package com.iotatlab.utils;

/**
 * CREATED BY Dream
 * DATE : 2018/7/23
 * MAIL : YANK.TENYOND@GMAIL.COM
 * FUNCTION :
 */
public final class Preconditions {

    private Preconditions(){}

    /**
   * Ensures that an object reference passed as a parameter to the calling method is not null.
   *
   * @param reference an object reference
   * @return the non-null reference that was validated
   * @throws NullPointerException if {@code reference} is null
   */
  public static <T> T checkNotNull(T reference) {
    if (reference == null) {
      throw new NullPointerException();
    }
    return reference;
  }

}
