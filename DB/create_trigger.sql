CREATE OR REPLACE TRIGGER manufacturer_before_ins
BEFORE INSERT ON manufacturer FOR EACH ROW
Begin
    :new.id := caloric_seq.nextval;
End;
