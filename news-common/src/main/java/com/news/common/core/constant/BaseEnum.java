package com.news.common.core.constant;

public interface BaseEnum<E extends Enum<?>, T> {
	T getValue();
	String getDisplayName();
}