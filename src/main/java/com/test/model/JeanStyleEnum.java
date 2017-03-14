package com.test.model;

public enum JeanStyleEnum {
        BOOTCUT, FLARE, RELAXED, SKINNY, STRAIGHT;

        @Override
        public String toString() {
            switch (this) {
                case BOOTCUT:
                    return "Bootcut";
                case FLARE:
                    return "Flare";
                case RELAXED:
                    return "Relaxed";
                case SKINNY:
                    return "Skinny";
                case STRAIGHT:
                    return "Straight";
                default:
                    return "";
            }
        }
    }

