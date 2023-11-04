package com.ace.ucv.service.parser.iface;

import com.ace.ucv.service.exception.ConfigurationLoaderException;

import java.io.File;

/**
 * Created by Andreea Draghici on 11/4/2023
 * Name of project: StudentManagement
 */
public interface IConfigLoader<T> {

    T loadConfiguration(File file) throws ConfigurationLoaderException;

    void inputCheck(File file) throws ConfigurationLoaderException;
}
