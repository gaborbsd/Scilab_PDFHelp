                      Scilab 5.1.0 Release_Notes
                      ==========================


- Graphics:
  Errors occuring when getting or setting graphic handles properties
  are now treated as any other Scilab errors.
  As a consequence, these kinds of errors are now interrupting Scilab
  scripts when occuring. Although this simplifies graphic error
  detection, it might break some scripts containing such errors.

- Scipad Debugger:
  The Scipad debugger is still broken because of code reorganization that has
  been performed in the Scilab core and rewriting of the Tcl interface. It has
  therefore been unplugged from Scipad and will be plugged back as soon as
  possible.

- Mac OS X port:
There are some known issues with the Mac OS X version:

   	* For now, Tcl/Tk has been disabled. The technical reason is because
	both Swing and Tk need to be driven by the Core Foundation
	(CFRunLoopRun) and it is tricky to handle both at the sametime.
	* As a consequence, TclSci (Scilab / Tcl interface) Scipad, GED
	and the variable editor are not working.

	* On a non-up-to-date Mac OS X, an error saying that the Dock Icon
	cannot be set can occur. Please update your Mac OS X Java.

	* Scilab starts in the background (ie behind other applications).

	* The merge of windows provided by the docking system does not work

	* Due to internal constraints, during the load of Scilab, it is normal
	to see the icon changing for a second.

	* On a fresh compiled version of Scilab, you could experience some
	issues with the unsfdcopy function. This might be related to the
	version of your compiler/libraries.

	* The refresh on some demos is not done correctly which can cause
	some refresh issues.

If you find bugs under Mac OS X which are not in this list, please submit them
on:
http://bugzilla.scilab.org/
Please provide the relevant part from /var/log/system.log

And patches are welcome!
