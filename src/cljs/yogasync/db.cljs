(ns yogasync.db)

(def default-db
  {:show-twirly? false
   :app-name "YOGASYNC"
   :classes [{:title "Keith Golden Zoom Yoga",
              :rrule {:freq "weekly",
                      :dtstart "2023-10-29T10:30:00"},
              ;an example of how to cancel a date
              :exdate ["2023-11-12T10:30:00"]
              :description "Keith's mobility-focused zoom classes meet every Sunday at 10AM.  Send Keith an e-mail to become a member."}
             ;i think Tami classes got fucked
             {:title "Tami Jacobs @ Guardian",
              :rrule {:freq "weekly",
                      :dtstart "2023-10-31T18:15:00"}
              :description "Katonah inspired classes at GUARDIAN"}
             {:title "Martha McAlpine Zoom Yoga"
              :rrule {:freq "weekly"
                      :byweekday ["mo" "we" "fr"]
                      :dtstart "2023-10-30T08:00"}
              :description "Kundalini Yoga"}
             ; maybe sarah too?
             {:title "Sarah Cook @ Soulage",
              :rrule {:freq "weekly"
                      :dtstart "2023-11-01T18:15"}
              :description "Right on"}
             {:title "Alex D'Agostino @ Soulage",
              :rrule {:freq "weekly"
                      :byweekday ["tu" "th"]
                      :dtstart "2023-10-31T09:30"}
              :description "Neato"}
             {:title "Sarah Cook Zoom Yoga",
              :rrule {:freq "weekly"
                      :byweekday ["mo" "we" "fr"]
                      :dtstart "2023-10-30T09:30"}
              :description "UHHH HUHHH"}
             {:title "Abir Alzante @ Brick Bodies",
              :rrule {:freq "weekly"
                      :dtstart "2023-10-31T12:30"}
              :description "Need to look into how this is priced out, seems complicated"}
             {:title "Sarah Cook @ Soulage",
              :rrule {:freq "weekly"
                      :dtstart "2023-10-29T09:30"}
              :description "uhhhh"}
             {:title "Lily Dwyer Begg Zoom Yoga",
              :rrule {:freq "weekly"
                      :dtstart "2023-10-29T10:00"}
              :description "Sunday Yoga Church"}
             {:title "Lily Dwyer Begg Zoom Yoga",
              :rrule {:freq "weekly"
                      :dtstart "2023-11-01T06:30"}
              :description "Wednesday Strong Flow"}
             {:title "Lily Dwyer Begg Zoom Yoga",
              :rrule {:freq "weekly"
                      :dtstart "2023-11-03T07:30"}
              :description "Friday Yoga HIIT"}]})
