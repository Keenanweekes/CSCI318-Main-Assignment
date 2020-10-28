class GenerateClass {
static public String generate(ObjectValue val) {
String success = "Success";
try {
genTestClass(val);
} catch (Exception e) {
success = e.toString();
}
return success;
}

static private TestClass genTestClass(ObjectValue val) throws Exception {
int arg0 = ((IntValue) val.get(0)).getVal();
String arg1 = ((StringValue) val.get(1)).getVal();
double arg2 = ((DoubleValue) val.get(2)).getVal();
Pet arg3 = genPet((ObjectValue) val.get(3));
return new TestClass(arg0, arg1, arg2, arg3);
}

static private Pet genPet(ObjectValue val) throws Exception {
String arg0 = ((StringValue) val.get(0)).getVal();
double arg1 = ((DoubleValue) val.get(1)).getVal();
IntValue[] argArr2 = ((ArrayValue<IntValue>) val.get(2)).getVal();
int[] arg2 = new int[argArr2.length];
for (int i = 0; i < argArr2.length; i++) arg2[i] = argArr2[i].getVal();
return new Pet(arg0, arg1, arg2);
}
}