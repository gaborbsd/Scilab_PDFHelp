// Scilab ( http://www.scilab.org/ ) - This file is part of Scilab
// Copyright (C) 2010 -  INRIA - Serge Steer
//
// This file must be used under the terms of the CeCILL.
// This source file is licensed as described in the file COPYING, which
// you should have received as part of this distribution.  The terms
// are also available at
// http://www.cecill.info/licences/Licence_CeCILL_V2.1-en.txt

function a = %p_cumsum(varargin)
    a = varargin(1)
    dims = size(a);

    nargs = size(varargin);
    select nargs
    case 1
        d = "*";
    case 2
        if or(varargin(2) == ["native", "double"]) then
            d = "*";
        else
            d = varargin(2);
        end
    case 3
        d = varargin(2);
    else
        error(msprintf(_("%s: Wrong number of input argument(s): %d to %d expected.\n"),"cumsum", 1, 3));
    end

    // Check second argument : d
    select type(d)
    case 1
        if size(d,'*') <> 1 then
            error(msprintf(_("%s: Wrong size for input argument #%d: A scalar expected.\n"),"cumsum", 2))
        end
        if int(d) <> d | d < 0 then
            error(msprintf(_("%s: Wrong value for input argument #%d: Integer >= %d expected.\n"),"cumsum", 2, 1))
        end
    case 10 
        if size(d,'*') <> 1 then
            error(msprintf(_("%s: Wrong size for input argument #%d: A string expected.\n"),"cumsum",2))
        end
        if and(d<>["r","c","*","m"]) then
            error(msprintf(_("%s: Wrong value for input argument #%d: Must be in the set {%s}.\n"),..
            "cumsum",2,"""*"",""r"",""c"",""m"""))
        end
        
        pos = [1,2,0,find(dims>1,1)];
        d=pos(find(d==["r","c","*","m"]))
        
    else
        error(msprintf(_("%s: Wrong type for input argument #%d: A string or scalar expected.\n"),"cumsum",2))
    end

    select d
    case 0 then
        for k=2:prod(dims)
            a(k)=a(k-1)+a(k)
        end
    case 1 then
        for k=1:dims(2)
            a(:,k)=cumsum(a(:,k))
        end
    case 2 then
        for k=1:dims(1)
            a(k,:)=cumsum(a(k,:))
        end
    else
        a=a
    end
endfunction
