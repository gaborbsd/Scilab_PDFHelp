// Scilab ( http://www.scilab.org/ ) - This file is part of Scilab
// Copyright (C) 2104 - Scilab Enterprises - Antoine ELIAS
// This file must be used under the terms of the CeCILL.
// This source file is licensed as described in the file COPYING, which
// you should have received as part of this distribution.  The terms
// are also available at
// http://www.cecill.info/licences/Licence_CeCILL_V2.1-en.txt
function h = %GridCons_i_h(i,v,h)
    if type(i)==10 then
        set(h,i,v)
    else
        error(msprintf(_("%s: Wrong type for input argument #%d.\n"),"%GridCons_i_h",1));
    end
endfunction
