c Scilab ( http://www.scilab.org/ ) - This file is part of Scilab
c Copyright (C) INRIA
c 
c This file must be used under the terms of the CeCILL.
c This source file is licensed as described in the file COPYING, which
c you should have received as part of this distribution.  The terms
c are also available at    
c http://www.cecill.info/licences/Licence_CeCILL_V2.1-en.txt

c SCILAB function blkfc1i
      subroutine intblkfc1i(id)
      logical getrhsvar, putlhsvar      
      include 'stack.h'
c
       nbvars=0
c*****************************************************
c      0-Check number of rhs and lhs arguments
c*****************************************************       
       minrhs=15
       maxrhs=15
       minlhs=2
       maxlhs=2
c

       if(.not.((rhs.ge.minrhs).and.(rhs.le.maxrhs))) then
          call erro('wrong number of rhs arguments')
          return
       endif
       if(.not.((lhs.ge.minlhs).and.(lhs.le.maxlhs))) then
          call erro('wrong number of lhs arguments')
          return
       endif

c*******************************************************
c      1-Get rhs parameters and set their Fortran types
c*******************************************************
       if(.not.getrhsvar( 1,'i', m1, n1, l1)) return
       if(.not.getrhsvar( 2,'i', m2, n2, l2)) return
       if(.not.getrhsvar( 3,'i', m3, n3, l3)) return
       if(.not.getrhsvar( 4,'i', m4, n4, l4)) return
       if(.not.getrhsvar( 5,'i', m5, n5, l5)) return
       if(.not.getrhsvar( 6,'i', m6, n6, l6)) return
       if(.not.getrhsvar( 7,'i', m7, n7, l7)) return
       if(.not.getrhsvar( 8,'i', m8, n8, l8)) return
       if(.not.getrhsvar( 9,'d', m9, n9, l9)) return
       if(.not.getrhsvar(10,'i',m10,n10,l10)) return
       if(.not.getrhsvar(11,'i',m11,n11,l11)) return
       if(.not.getrhsvar(12,'i',m12,n12,l12)) return
       if(.not.getrhsvar(13,'d',m10,n13,l13)) return
       if(.not.getrhsvar(14,'i',m11,n14,l14)) return
       if(.not.getrhsvar(15,'i',m12,n15,l15)) return

c*****************************************************
c      2-If necessary, create additional variables 
c          (working arrays, default values, ...)
c*****************************************************
    
c******************************************************
c      3-Routine call
c      stk  <-> double
c      sstk <-> real
c      istk <-> integer
c      cstk <-> character
c*****************************************************
       call blkfc1(istk(l1),istk(l2),istk(l3) ,istk(l4),
     $ istk( l5),istk( l6),istk( l7),istk( l8), stk(l9),
     $ istk(l10),istk(l11),istk(l12), stk(l13),
     $ istk(l14),istk(l15))
c******************************************************
c      5- Set lhs parameters
c******************************************************
       lhsvar( 1)= 9
       lhsvar( 2)= 14

c******************************************************
c      6-Sending lhs variables to Scilab
c******************************************************
       if(.not.putlhsvar()) return
c      .
       return
      end
c			======================================
