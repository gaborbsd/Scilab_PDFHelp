// =============================================================================
// Scilab ( http://www.scilab.org/ ) - This file is part of Scilab
// Copyright (C) 2008 - DIGITEO - Allan CORNET
//
//  This file is distributed under the same license as the Scilab package.
// =============================================================================

// <-- ENGLISH IMPOSED -->
// <-- CLI SHELL MODE -->

// <-- Non-regression test for bug 3532 -->
//
// <-- Bugzilla URL -->
// http://bugzilla.scilab.org/show_bug.cgi?id=3532
//
// <-- Short Description -->
// link does not manage column vector as scilab 4.x

ilib_verbose(0);

if getos() == 'Windows' then
  warning('off');
  Cfunctions = '';
  ierr = execstr("link(SCI+''/bin/scilab_windows.dll'',Cfunctions,''c'');",'errcatch');
  if ierr <> 235 then pause,end
  ulink();
  
  Cfunctions = [];
  ierr = execstr("link(SCI+''/bin/scilab_windows.dll'',Cfunctions,''c'');",'errcatch');
  if ierr <> 999 then pause,end
  ulink();
  
  Cfunctions = ['Windows_Main'];
  link(SCI+'/bin/scilab_windows.dll',Cfunctions,'c');
  ulink();
  
  Cfunctions = ['Windows_Main','Console_Main','createInnosetupMutex','closeInnosetupMutex'];
  link(SCI+'/bin/scilab_windows.dll',Cfunctions,'c');
  ulink();
  
  Cfunctions = ['Windows_Main';'Console_Main';'createInnosetupMutex';'closeInnosetupMutex'];
  link(SCI+'/bin/scilab_windows.dll',Cfunctions,'c');
  ulink();
  
  Cfunctions = ['Windows_Main','Console_Main';'createInnosetupMutex','closeInnosetupMutex'];
  ierr = execstr("link(SCI+''/bin/scilab_windows.dll'',Cfunctions,''c'');",'errcatch');
  if ierr <> 999 then pause,end
  ulink();
  warning('on');
end
// =============================================================================
