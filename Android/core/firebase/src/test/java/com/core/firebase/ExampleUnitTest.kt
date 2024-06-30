package com.core.firebase

import com.core.firebase.model.UserDTO
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UserDataSourceImplTest {

    @Mock
    private lateinit var mockDatabaseReference: DatabaseReference

    @Mock
    private lateinit var mockDataSnapshot: DataSnapshot

    @Mock
    private lateinit var mockValueEventListener: ValueEventListener

    @InjectMocks
    private lateinit var userDataSourceImpl: UserDataSourceImpl

    @Before
    fun setup() {
        userDataSourceImpl = UserDataSourceImpl(mockDatabaseReference)
    }

    @Test
    fun testSignup() {
        val id = "testUser"
        val pwd = "password"
        val name = "Test User"
        val tel = "1234567890"
        val duty = "user"
        val onSuccess = Mockito.mock(Function1::class.java) as (Boolean) -> Unit

        userDataSourceImpl.signup(id, pwd, name, tel, duty, onSuccess)

        Mockito.verify(mockDatabaseReference.child("users").child(id).child("password"))
            .setValue(pwd)
        Mockito.verify(mockDatabaseReference.child("users").child(id).child("name")).setValue(name)
        Mockito.verify(mockDatabaseReference.child("users").child(id).child("tel")).setValue(tel)
        Mockito.verify(mockDatabaseReference.child("users").child(id).child("duty")).setValue(duty)

        Mockito.`when`(mockDataSnapshot.childrenCount).thenReturn(1)
        mockValueEventListener.onDataChange(mockDataSnapshot)

        Mockito.verify(mockDatabaseReference.child("users").child(id).child("duty"))
            .setValue("admin")
        Mockito.verify(onSuccess).invoke(true)
    }

    @Test
    fun testCheckSignup() {
        val tel = "1234567890"
        val onExisted = Mockito.mock(Function1::class.java) as (Boolean) -> Unit

        userDataSourceImpl.checkSignup(tel, onExisted)

        val query = mockDatabaseReference.child("users").orderByChild("tel").equalTo(tel)
        Mockito.verify(query)
            .addListenerForSingleValueEvent(Mockito.any(ValueEventListener::class.java))

        Mockito.`when`(mockDataSnapshot.exists()).thenReturn(true)
        mockValueEventListener.onDataChange(mockDataSnapshot)

        Mockito.verify(onExisted).invoke(true)
    }

    @Test
    fun testCheckId() {
        val id = "testUser"
        val onExisted = Mockito.mock(Function1::class.java) as (Boolean) -> Unit

        userDataSourceImpl.checkId(id, onExisted)

        Mockito.verify(mockDatabaseReference.child(id))
            .addListenerForSingleValueEvent(Mockito.any(ValueEventListener::class.java))

        Mockito.`when`(mockDataSnapshot.exists()).thenReturn(true)
        mockValueEventListener.onDataChange(mockDataSnapshot)

        Mockito.verify(onExisted).invoke(true)
    }

    @Test
    fun testLogin() {
        val id = "testUser"
        val onLogin = Mockito.mock(Function1::class.java) as (UserDTO) -> Unit

        userDataSourceImpl.login(id, onLogin)

        val query = mockDatabaseReference.child(id).equalTo(id)
        Mockito.verify(query).addValueEventListener(Mockito.any(ValueEventListener::class.java))

        Mockito.`when`(mockDataSnapshot.exists()).thenReturn(true)
        Mockito.`when`(mockDataSnapshot.child(id).getValue(UserDTO::class.java))
            .thenReturn(UserDTO(id, "Test User", "1234567890", "user", "NORMAL"))

        mockValueEventListener.onDataChange(mockDataSnapshot)

        Mockito.verify(onLogin)
            .invoke(UserDTO(id, "Test User", "1234567890", "1234567890", "NORMAL"))
    }
}
