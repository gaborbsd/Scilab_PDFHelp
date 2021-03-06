// Scilab ( http://www.scilab.org/ ) - This file is part of Scilab
// Copyright (C) INRIA
//
// This file must be used under the terms of the CeCILL.
// This source file is licensed as described in the file COPYING, which
// you should have received as part of this distribution.  The terms
// are also available at
// http://www.cecill.info/licences/Licence_CeCILL_V2.1-en.txt

function M2=%s_q_hm(M1,M2)

    if size(M1,"*")<>1 then
        error(msprintf(_("%s: Wrong size for input argument #%d.\n"),"%s_q_hm",1));
    else
        M2("entries")=M1.\M2("entries")
    end
endfunction
