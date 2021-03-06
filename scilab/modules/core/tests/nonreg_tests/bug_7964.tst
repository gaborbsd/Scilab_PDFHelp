// =============================================================================
// Scilab ( http://www.scilab.org/ ) - This file is part of Scilab
// Copyright (C) 2010 - DIGITEO - Allan CORNET
//
//  This file is distributed under the same license as the Scilab package.
// =============================================================================

// <-- Non-regression test for bug 7964 -->
//
// <-- JVM MANDATORY -->
// 
// <-- Bugzilla URL -->
// http://bugzilla.scilab.org/show_bug.cgi?id=7964
//
// <-- Short Description -->
// errclear(2) cleared all errors and not only error 2 as written in help
//

ierr = execstr("unknown", "errcatch");
if ierr <> 4 then pause, end
errclear();
if lasterror() <> [] then pause,end

ierr = execstr("unknown", "errcatch");
if ierr <> 4 then pause, end
errclear(4);
[msgerr, valerr] = lasterror();
if msgerr <> [] then pause, end
if valerr <> 0 then pause, end

ierr = execstr("unknown", "errcatch");
if ierr <> 4 then pause, end
errclear(400);
[msgerr, valerr] = lasterror();
if msgerr <> msprintf(_("Undefined variable: %s\n"), "unknown") then pause, end
if valerr <> 4 then pause, end
