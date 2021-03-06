// =============================================================================
// Scilab ( http://www.scilab.org/ ) - This file is part of Scilab
// Copyright (C) ????-2008 - INRIA
//
//  This file is distributed under the same license as the Scilab package.
// =============================================================================

// <-- Non-regression test for bug 568 -->
//
// <-- Bugzilla URL -->
// http://bugzilla.scilab.org/show_bug.cgi?id=568
//
// <-- Short Description -->
//    when I have defined a variable like this :
//    -->aa=zeros(4,4,11);
//    I can't insert a part of this hypermatrix in a indexed
//    undefined variable bb.
//    -->bb(1,1,:)=aa(1,1,:) ;
//    This appears in all operating system and version 2.7, 2.7.2
//    and 2.8 alpha
//
//    fixed in CVS version


// exec( "/home/huynh/poubelle/testNonReg/bug568.sce");
// exec("e:\testNonReg\bug568.sce");

aa=zeros(4,4,11);
if execstr('bb(1,1,:)=aa(1,1,:)','errcatch') <> 15 then pause,end
