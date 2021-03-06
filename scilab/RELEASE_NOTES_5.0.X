                      Scilab 5.0 Release_Notes
                      ========================

List of missing/buggy features:

- Uicontrols ascendant incompatibilities:
    * Figure size has to be increased because of the automatic insertion of
      a menubar and the height of the docking bar.
    * Popup uicontrols width has to be increased because of the little triangle
      used to select a new value.

- Graphics:
    * Graphic rendering is slower in a large graphic window than in a small 
      one.
    * Rendering is slow or not functional on some
      computer / graphic card / graphic card driver combination.
      Errors often appear on Intel cards under Linux and also with 
      old Nvidia or ATI drivers.
    * By default arcs and circles are now approximated using a constant number 
	  of lines.
      To switch to real arcs, set their arc_drawing_method property to "nurbs".
    * Sector boundary angles of arc objects are now specified in degree whereas
	  it used to be specified in degree/64.
    * The text_box property of text objects now refers to user coordinates even
	  in axes_reverse mode.
    * Bode routine creates only the two needed axes instead of four in previous
	  versions.
    * graypolarplot routine is now constructed using a surface instead of arcs.
    * Some old postcript tools are not used anymore in Scilab and have been
	  removed.
      The list of file removed form the Scilab bin directory is:
      BEpsf, Blatdoc, Blatdocs, Blatexpr, Blatexpr2, Blatexpr2, Blatexprs, Blpr
	  and lpr
    * Pixel drawing mode property of figures is not compatible with Scilab 4.
      The result in Scilab 5 is inverted compared to the one in Scilab 4.
      For example the result of a white object drawn above a white background
	  in "xor" mode will appear black (it was still white in Scilab 4).
      Moreover, the logical operators now works on the entire figure.
      In practice, the "xor" mode can be generally replaced by the "equiv" one.      


- It is strongly recommended to update graphic cards drivers to the latest 
	revision:
    * For ATI cards: http://ati.amd.com/support/driver.html
    * For Intel cards: http://www.intel.com/support/graphics/
    * For Matrox cards: http://www.matrox.com/graphics/en/support/drivers/
    * For NVIDIA cards: http://www.nvidia.com/content/drivers/drivers.asp
    * For S3 cards: http://www.s3graphics.com/en/resources/drivers/index.jsp  
    * For SiS cards: http://www.sis.com/download/
    * For VIA cards: http://www.viaarena.com/default.aspx?PageID=2


- Scicos:
    * Scicos code has been updated to the new event handling functions and code
      (please see "Event Handling Functions" section in "CHANGES") 
    * Tcl/Tk features are still inactive (eg. "Palette Tree")

- The Scipad debugger is broken because of code reorganization that has been
  performed in the Scilab core and rewriting of the Tcl interface. It has
  therefore been unplugged from Scipad and will be plugged back as soon as
  possible.

- Printing can crash under Linux plateforms with recent versions of Cups.
  See http://bugzilla.scilab.org/show_bug.cgi?id=3309

- Scilab localization system is implemented, however not all the strings have
  been added to the translation engine and therefore not translated.

- Dynamic/incremental link bugs on unusual archs/compiler or with modified 
  flags and still have issues with the binary version of Scilab.

- It is not (yet, we hope) possible to build a file documentation file 
  and add it into the documentation without rebuilding the whole documentation.

- MacOS X version has some critical issues which will be probably addressed in 
  Scilab v 5.1

- The search in the help is not done on the Scicos help.

- Some Linux distributions with SELinux enabled may prevent Scilab from running
  under the default security context. Newer Linux distributions have enabled 
  new kernel security extensions from the SELinux project at the NSA. These
  extensions allow finer-grained control over system security. However, 
  SELinux also changes some default system behaviors, such as shared library 
  loading, that can be problematic to third party programs.

  If you receive the error message:
  "cannot restore segment prot after reloc: Permission denied"
  when launching Scilab, then your SELinux configuration is preventing Scilab
  from launching.

  To rectify this issue, you can either:
    * Change the default security context for Scilab by issuing the command:
      chcon -t texrel_shlib_t <scilab_path>/thirdparty/java/lib/i386/server/libjvm.so
      chcon -t texrel_shlib_t ~/flexdock/RubberBand.so
    * Disabling SELinux altogether by setting the line
      SELINUX=disabled
      in your /etc/sysconfig/selinux file.

  For more information about SELinux, please consult your Linux distribution
  vendor.

