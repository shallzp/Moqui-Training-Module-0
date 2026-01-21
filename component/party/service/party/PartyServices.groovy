//Creating new value of Person entity
def person = ec.entity.makeValue("moqui.training.Person")
person.setFields(context, true, null, null)

//Validate Fields
if (!person.partyId) ec.message.addError("Party ID is required")
if (!person.firstName) ec.message.addError("First Name is required")
if (!person.lastName) ec.message.addError("Last Name is required")

//Checking if partyId exists in Party entity
def party = ec.entity.find("moqui.training.Party").condition("partyId", person.partyId).one()
if (party == null) {
    ec.message.addError("Cannot create Person: Party [${person.partyId}] does not exist.")
    return
}

person.create() //Finalize creation

//Success Message
response = "Person ${person.firstName} ${person.lastName} created successfully!"