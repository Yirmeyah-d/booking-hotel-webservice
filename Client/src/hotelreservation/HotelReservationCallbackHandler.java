
/**
 * HotelReservationCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package hotelreservation;

    /**
     *  HotelReservationCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class HotelReservationCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public HotelReservationCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public HotelReservationCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for register method
            * override this method for handling normal response from register operation
            */
           public void receiveResultregister(
                    hotelreservation.HotelReservationStub.RegisterResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from register operation
           */
            public void receiveErrorregister(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for restCallRegister method
            * override this method for handling normal response from restCallRegister operation
            */
           public void receiveResultrestCallRegister(
                    hotelreservation.HotelReservationStub.RestCallRegisterResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from restCallRegister operation
           */
            public void receiveErrorrestCallRegister(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for restCallBooking method
            * override this method for handling normal response from restCallBooking operation
            */
           public void receiveResultrestCallBooking(
                    hotelreservation.HotelReservationStub.RestCallBookingResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from restCallBooking operation
           */
            public void receiveErrorrestCallBooking(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for book method
            * override this method for handling normal response from book operation
            */
           public void receiveResultbook(
                    hotelreservation.HotelReservationStub.BookResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from book operation
           */
            public void receiveErrorbook(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for checkHotelsAvailable method
            * override this method for handling normal response from checkHotelsAvailable operation
            */
           public void receiveResultcheckHotelsAvailable(
                    hotelreservation.HotelReservationStub.CheckHotelsAvailableResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from checkHotelsAvailable operation
           */
            public void receiveErrorcheckHotelsAvailable(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for authenticate method
            * override this method for handling normal response from authenticate operation
            */
           public void receiveResultauthenticate(
                    hotelreservation.HotelReservationStub.AuthenticateResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from authenticate operation
           */
            public void receiveErrorauthenticate(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for restCallAuthenticate method
            * override this method for handling normal response from restCallAuthenticate operation
            */
           public void receiveResultrestCallAuthenticate(
                    hotelreservation.HotelReservationStub.RestCallAuthenticateResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from restCallAuthenticate operation
           */
            public void receiveErrorrestCallAuthenticate(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for restCallCheckExistingReservation method
            * override this method for handling normal response from restCallCheckExistingReservation operation
            */
           public void receiveResultrestCallCheckExistingReservation(
                    hotelreservation.HotelReservationStub.RestCallCheckExistingReservationResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from restCallCheckExistingReservation operation
           */
            public void receiveErrorrestCallCheckExistingReservation(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for checkExistingReservation method
            * override this method for handling normal response from checkExistingReservation operation
            */
           public void receiveResultcheckExistingReservation(
                    hotelreservation.HotelReservationStub.CheckExistingReservationResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from checkExistingReservation operation
           */
            public void receiveErrorcheckExistingReservation(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for restCallHotelsAvailable method
            * override this method for handling normal response from restCallHotelsAvailable operation
            */
           public void receiveResultrestCallHotelsAvailable(
                    hotelreservation.HotelReservationStub.RestCallHotelsAvailableResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from restCallHotelsAvailable operation
           */
            public void receiveErrorrestCallHotelsAvailable(java.lang.Exception e) {
            }
                


    }
    