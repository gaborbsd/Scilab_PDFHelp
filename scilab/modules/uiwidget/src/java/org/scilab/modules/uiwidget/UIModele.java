/*
 * Scilab ( http://www.scilab.org/ ) - This file is part of Scilab
 * Copyright (C) 2012 - Scilab Enterprises - Calixte DENIZET
 *
 * This file must be used under the terms of the CeCILL.
 * This source file is licensed as described in the file COPYING, which
 * you should have received as part of this distribution.  The terms
 * are also available at
 * http://www.cecill.info/licences/Licence_CeCILL_V2-en.txt
 *
 */

package org.scilab.modules.uiwidget;

import java.io.File;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import javax.swing.UIManager;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**

<modele namespace="foo">

<UIFrame modele-name="Machin">
...
</UIFrame>

<UIButton modele-name="Truc">
...
</UIButton>
</modele>

**/

public class UIModele {

    private static final Map<String, UIModele> modeles = new HashMap<String, UIModele>();

    Map<String, UIFakeComponent> m;

    public UIModele() {
        m = new HashMap<String, UIFakeComponent>();
    }

    public static void add(String uri, String modeleName, UIFakeComponent component) {
        UIModele modele = modeles.get(uri);
        if (modele == null) {
            modele = new UIModele();
            modeles.put(uri, modele);
        }

        modele.m.put(modeleName, component);
    }

    public static UIFakeComponent get(String uri, String modeleName, Attributes attrs) {
        UIModele modele = modeles.get(uri);
        if (modele == null) {
            return null;
        }

        UIFakeComponent ui = modele.m.get(modeleName);
        if (attrs != null && attrs.getLength() != 0) {
            UIFakeComponent uic = (UIFakeComponent) ui.clone();
            uic.addAttributes(attrs);

            return uic;
        }

        return ui;
    }

    public static UIComponent get(String uri, String modeleName, final UIComponent parent, final Map<String, Map<String, String>> style) throws UIWidgetException {
        return get(uri, modeleName, parent, null, style);
    }

    public static UIComponent get(String uri, String modeleName, final UIComponent parent, final StringMap attributes, final Map<String, Map<String, String>> style) throws UIWidgetException {
        UIModele modele = modeles.get(uri);
        if (modele == null) {
            return null;
        }

        final UIFakeComponent c = modele.m.get(modeleName);
        if (c == null) {
            return null;
        }

        final UIComponent[] ptr = new UIComponent[1];
        try {
            javax.swing.SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    try {
                        ptr[0] = c.getUIComponent(parent, attributes, style);
                    } catch (Exception e) {
                        System.err.println(e);
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) { }

        return ptr[0];
    }
}