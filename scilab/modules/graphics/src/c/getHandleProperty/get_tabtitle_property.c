/*
 * Scilab ( http://www.scilab.org/ ) - This file is part of Scilab
 * Copyright (C) 2014 - Scilab Enterprises - Antoine ELIAS
 *
 * This file must be used under the terms of the CeCILL.
 * This source file is licensed as described in the file COPYING, which
 * you should have received as part of this distribution.  The terms
 * are also available at
 * http://www.cecill.info/licences/Licence_CeCILL_V2.1-en.txt
 *
 */

#include "getHandleProperty.h"
#include "returnProperty.h"
#include "Scierror.h"
#include "localization.h"
#include "TitlePositionType.h"

#include "getGraphicObjectProperty.h"
#include "graphicObjectProperties.h"

/*------------------------------------------------------------------------*/
int get_title_scroll_property(void* _pvCtx, int iObjUID)
{
    int scroll = 0;
    int* piScroll = &scroll;

    getGraphicObjectProperty(iObjUID, __GO_UI_TITLE_SCROLL__, jni_bool, (void **)&piScroll);
    if (piScroll == NULL)
    {
        Scierror(999, _("'%s' property does not exist for this handle.\n"), "title_scroll");
        return FALSE;
    }

    if (scroll)
    {
        return sciReturnString(_pvCtx, "on");
    }
    else
    {
        return sciReturnString(_pvCtx, "off");
    }
}
/*------------------------------------------------------------------------*/
int get_title_position_property(void* _pvCtx, int iObjUID)
{
    int iPos = 0;
    int* piPos = &iPos;
    getGraphicObjectProperty(iObjUID, __GO_UI_TITLE_POSITION__, jni_int, (void **)&piPos);

    if (piPos == NULL)
    {
        Scierror(999, _("'%s' property does not exist for this handle.\n"), "title_position");
        return -1;
    }

    switch (iPos)
    {
        default :
        case TITLE_TOP :
            return sciReturnString(_pvCtx, "top");
        case TITLE_LEFT :
            return sciReturnString(_pvCtx, "left");
        case TITLE_BOTTOM :
            return sciReturnString(_pvCtx, "bottom");
        case TITLE_RIGHT :
            return sciReturnString(_pvCtx, "right");
    }
}
