// =============================================================================
// Scilab ( http://www.scilab.org/ ) - This file is part of Scilab
// Copyright (C) 2012 - Scilab Enterprises - Vincent COUVERT
//
//  This file is distributed under the same license as the Scilab package.
// =============================================================================
//
// <-- TEST WITH GRAPHIC -->
//
// <-- Non-regression test for bug 11388 -->
//
// <-- Bugzilla URL -->
// http://bugzilla.scilab.org/show_bug.cgi?id=11388
//
// <-- Short Description -->
// Calling xsetech(frect=[0,0,100,100]) failed.

assert_checktrue(execstr("xsetech(frect=[0,0,100,100])", "errcatch") == 0);
