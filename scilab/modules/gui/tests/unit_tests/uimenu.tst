// Scilab ( http://www.scilab.org/ ) - This file is part of Scilab
// Copyright (C) 2009 - DIGITEO - Vincent COUVERT
//
// This file must be used under the terms of the CeCILL.
// This source file is licensed as described in the file COPYING, which
// you should have received as part of this distribution.  The terms
// are also available at
// http://www.cecill.info/licences/Licence_CeCILL_V2.1-en.txt

// <-- TEST WITH GRAPHIC -->

// Simple example with Checked menu
h = uimenu("parent", gcf(), "label", "parent");
h1 = uimenu("parent", h, "label", "child1", "checked", "off");
h2 = uimenu("parent", h, "label", "child2");

// Checked menu which becomes a parent menu
h = uimenu("parent", gcf(), "label", "parent");
h1 = uimenu("parent", h, "label", "child1");
h2 = uimenu("parent", h, "label", "child2", "checked", "off");
h3 = uimenu("parent", h1, "label", "subchild11", "checked", "off");
h4 = uimenu("parent", h2, "label", "subchild21", "checked", "on");

h = uimenu("label","test");
assert_checkequal(h.parent, gcf());
assert_checkequal(h.enable, "on");
assert_checkequal(h.label, "test");
assert_checkequal(h.visible, "on");
assert_checkequal(h.callback, "");
assert_checkequal(h.callback_type, 0);
assert_checkequal(h.checked, "off");
assert_checkequal(h.tag, "");

f=scf();
h = uimenu("Parent", f, ..
    "Enable","off",..
    "Label", "test", ..
    "Visible","off",..
    "Callback","disp(1)",..
    "Checked", "on", ..
    "Tag", "hello");
assert_checkequal(h.parent, f);
assert_checkequal(h.enable, "off");
assert_checkequal(h.label, "test");
assert_checkequal(h.visible, "off");
assert_checkequal(h.callback, "disp(1)");
assert_checkequal(h.callback_type, 0);
assert_checkequal(h.checked, "on");
assert_checkequal(h.tag, "hello");

// Add a menu in default figure
f=gdf();
m=uimenu(f, "label", "windows");
m1=uimenu(m, "label", "operations");
f=scf(50);
assert_checkequal(f.children(2).Label, "windows");
assert_checkequal(f.children(2).children.Label, "operations");
