package com.practice.emaxx.geometry;

import java.util.Objects;

/**
 * Created by vvats on 28/10/18.
 */
public class Pair<L, R> {

    private L left;
    private R right;

    public Pair(final L left, final R right) {
        this.left = left;
        this.right = right;
    }

    public Pair(final Pair<L, R> pair) {
        this.setLeft(pair.left);
        this.setRight(pair.right);
    }

    public L getLeft() {
        return this.left;
    }

    public R getRight() {
        return this.right;
    }

    public void setLeft(final L left) {
        this.left = left;
    }

    public void setRight(final R right) {
        this.right = right;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Pair<?, ?> pair = (Pair<?, ?>)o;
        return Objects.equals(left, pair.left) &&
                Objects.equals(right, pair.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append(left);
        sb.append(", ").append(right);
        sb.append('}');
        return sb.toString();
    }
}
