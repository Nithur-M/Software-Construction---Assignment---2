package kln.se.ass2.logfile;

import kln.se.ass2.mail.Mail;
import kln.se.ass2.logvariable.Log;
import kln.se.ass2.logvariable.Logvariables;
import kln.se.ass2.textcontent.Textfile;
import kln.se.ass2.textcontent.WriteFile;

import java.util.List;

public class Firstreadinglog extends Logfilehandler {
   private final Textfile textfile;
    Log log;
    private final Mail sentmail;
    public Firstreadinglog(Textfile textfile, Log log,Mail sentmail) {
      this.textfile=textfile;
      this.log=log;
      this.sentmail=sentmail;
    }

    public void errorchecking(String logfilepath) {

        int errorstate=0;
        List<Logvariables> loglines = super.readlogfile(logfilepath);
        for (Logvariables s : loglines) {
            if ((s.getLoglevel()).contains("ERROR")) {


                if(errorstate==0) {
                    System.out.println("Error Found");
                    errorstate = 1;
                   sentmail.sendmail();

                }

            }
        }

        log = loglines.get(loglines.size() - 1);
        new WriteFile().writetofile(log.getTimestatmp());
    }




    }

