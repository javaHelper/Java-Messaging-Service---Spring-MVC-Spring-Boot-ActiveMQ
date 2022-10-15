# Java-Messaging-Service---Spring-MVC-Spring-Boot-ActiveMQ

- Add below in `vi activemq.xml`

```xml
<plugins>
			<!-- Configure authentication; Username, passwords and groups -->
			<simpleAuthenticationPlugin>
				<users>
					<authenticationUser username="admin" password="admin" groups="admins"/>
				</users>
			</simpleAuthenticationPlugin>

			<!--  Lets configure a destination based authorization mechanism -->
			<authorizationPlugin>
				<map>
					<authorizationMap>
						<authorizationEntries>
							<authorizationEntry queue=">" read="admins" write="admins" admin="admins" />
							<authorizationEntry topic=">" read="admins" write="admins" admin="admins" />
						</authorizationEntries>
					</authorizationMap>
				</map>
			</authorizationPlugin>
		</plugins>
```

- also change credentials in `vi credentials.properties`.
